package com.chat.OneToOneChat.repository;

import com.chat.OneToOneChat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {
     Optional<ChatRoom> findBySenderIdAndRecipientId(String  senderId, String recipientId);
}
