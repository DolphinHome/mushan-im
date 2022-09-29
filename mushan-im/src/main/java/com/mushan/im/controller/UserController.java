package com.mushan.im.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mushan.im.config.MessageHandler;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/list")
    @CrossOrigin
    public Object list(){
        return MessageHandler.SESSIONS.keySet();
    }


}
