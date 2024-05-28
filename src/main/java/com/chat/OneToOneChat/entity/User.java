package com.chat.OneToOneChat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nickName;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Status status;
}
