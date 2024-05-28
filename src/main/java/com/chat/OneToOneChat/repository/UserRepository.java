package com.chat.OneToOneChat.repository;

import com.chat.OneToOneChat.entity.Status;
import com.chat.OneToOneChat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String > {
    List<User> findAllByStatus(Status status);
    boolean existsByNickName(String name);
    User findByNickName(String nickName);
}
