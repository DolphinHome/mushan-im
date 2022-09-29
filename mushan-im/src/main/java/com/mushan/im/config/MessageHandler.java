package com.mushan.im.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mushan.im.pojo.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MessageHandler extends TextWebSocketHandler {


    public static final Map<String, WebSocketSession> SESSIONS = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws
            Exception {
        String uid = session.getAttributes().get("uid").toString();
// 将当前用户的session放置到map中，后面会使用相应的session通信
        SESSIONS.put(uid, session);
        //给所有的在线用户推送
        allSendMessage(uid);
    }

    //给所有在线用户推送某个用户的登录消息
    private void allSendMessage(String user){
        Set<String> keys = SESSIONS.keySet();
        if (!CollectionUtils.isEmpty(keys)){
            keys.forEach(res->{
                if (StringUtils.isNoneEmpty(res) && !res.equals(user) ){
                    WebSocketSession webSocketSession = SESSIONS.get(res);
                    try {
                       if (webSocketSession != null){
                           Message message = new Message();
                           message.setType(0);
                           message.setMsg("您的好友"+user+"以上线。");
                           webSocketSession.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
                       }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        SESSIONS.remove(session.getAttributes().get("uid").toString());

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       Message mess = JSON.parseObject(message.getPayload(), Message.class);
       this.sendMessage(mess);
    }

    //聊天
    private void sendMessage(Message message){
        String to = message.getTo();
        WebSocketSession webSocketSession = SESSIONS.get(to);
        if (webSocketSession != null){
            try {
                webSocketSession.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
