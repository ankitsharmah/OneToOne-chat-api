package com.chat.OneToOneChat.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {


    private long id;
    private String senderId;
    private String recipientId;
    private String content;
}
