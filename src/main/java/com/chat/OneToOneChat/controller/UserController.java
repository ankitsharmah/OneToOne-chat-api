package com.chat.OneToOneChat.controller;


import com.chat.OneToOneChat.entity.User;
import com.chat.OneToOneChat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user){
        System.out.println("this is saving");
        userService.saveUser(user);
        return user;
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllConnectUser(){
        return ResponseEntity.ok().body(userService.activeUser());
    }
}
