package com.eddicorp.ifaspring.account.entity;

import com.eddicorp.ifaspring.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @JsonIgnore
    private String email;
    @Getter
    @JsonIgnore
    private String password;
    @Getter
    private String nickName;

    public Account(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }
}
