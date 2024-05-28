package com.chat.OneToOneChat.service;

import com.chat.OneToOneChat.entity.ChatMessage;
import com.chat.OneToOneChat.repository.ChatMassageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMassageService {
    private final ChatMassageRepository chatMassageRepository;
    private final ChatRoomService chatRoomService;

//    public ChatMessage save(ChatMessage chatMessage){
//            var chatId=chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(),true).orElseThrow();
//
//            chatMessage.setChatId(chatId);
//            chatMassageRepository.save(chatMessage);
//
//            return chatMessage;
//    }
public ChatMessage save(ChatMessage chatMessage) {
    // Get the chat room ID, or throw an exception if not found
    System.out.println("this is id "+chatMessage.getSenderId());
    System.out.println("this is rese "+chatMessage.getRecipientId());

    String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
            .orElseThrow(() -> new IllegalArgumentException("Chat room ID not found"));

    // Set the chat ID in the chat message and save it
    chatMessage.setChatId(chatId);
    chatMassageRepository.save(chatMessage);

    return chatMessage;
}

    public List<ChatMessage> findChatMessage(String  senderId,
                                             String  recipientId){

        var chatId= chatRoomService.getChatRoomId(
                senderId,
                recipientId,
                false
        );

        return chatId.map(chatMassageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
