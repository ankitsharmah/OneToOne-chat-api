package com.chat.OneToOneChat.service;

import com.chat.OneToOneChat.entity.ChatRoom;
import com.chat.OneToOneChat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(
            String senderId,String recipientId,boolean createNewRoomIfNotExists
    ){
            return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                    .map(ChatRoom::getChatId)
                    .or(()->{
                        if (createNewRoomIfNotExists){
                            var chatId = createChat(senderId,recipientId);
                            return Optional.of(chatId);
                        }
                        return Optional.empty();
                    });
    }

    private String createChat(String senderId, String recipientId) {
        System.out.println("making chat id");
        var chatId= String.format("%s_%s",senderId,recipientId);
        System.out.println("chat has been made");
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();


        ChatRoom recipientSender= ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        System.out.println("recipientSender has been made");

        chatRoomRepository.save(senderRecipient);
        System.out.println("senderrecipient has been saved");

        chatRoomRepository.save(recipientSender);
        System.out.println("recipientSender has been saved");

        return chatId;
    }
}
