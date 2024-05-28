package com.chat.OneToOneChat.controller;

import com.chat.OneToOneChat.entity.ChatMessage;
import com.chat.OneToOneChat.entity.ChatNotification;
import com.chat.OneToOneChat.service.ChatMassageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {
    private final ChatMassageService chatMassageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ){
        System.out.println("im called from front end");
        System.out.println("this is id "+chatMessage.getSenderId());
        System.out.println("this is rese "+chatMessage.getRecipientId());
        ChatMessage savedMsg = chatMassageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getContent()
                )
        );
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(
            @PathVariable String senderId,
            @PathVariable String recipientId
    ){
        System.out.println("in sender recipenr caht controller");
        return ResponseEntity.ok(chatMassageService.findChatMessage(senderId,recipientId));
    }
}
