package com.chat.OneToOneChat.repository;

import com.chat.OneToOneChat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMassageRepository extends JpaRepository<ChatMessage,String> {
    List<ChatMessage> findByChatId(String s);
}
