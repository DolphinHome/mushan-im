package com.mushan.im.pojo;

public class Message {

    private String to; //给谁
    private String source; //来源
    private Integer type;//类型  0 给所有人发送消息  1 单个人发送消息  2 视频通话
    private String msg; //消息内容


    @Override
    public String toString() {
        return "Message{" +
                "to='" + to + '\'' +
                ", source='" + source + '\'' +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
