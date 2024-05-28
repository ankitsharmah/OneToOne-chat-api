package com.chat.OneToOneChat.service;

import com.chat.OneToOneChat.entity.Status;
import com.chat.OneToOneChat.entity.User;
import com.chat.OneToOneChat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user){
        if (!userRepository.existsByNickName(user.getNickName())){
            System.out.println("this is exists by "+userRepository.existsByNickName(user.getNickName()));
            user.setStatus(Status.ONLINE);
            userRepository.save(user);
        }
        else {
            User receivedUser = userRepository.findByNickName(user.getNickName());
            receivedUser.setStatus(Status.ONLINE);
            userRepository.save(receivedUser);

        }

    }

    public void disconnect(User user){
        var storedUser=userRepository.findByNickName(user.getNickName());
        if (storedUser != null){
            System.out.println("this is not null "+storedUser);
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> activeUser(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
