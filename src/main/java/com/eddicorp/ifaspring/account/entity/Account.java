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
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String email;
    private String nickName;
    private Long oauthId;
    private String oauthPlatform;

    public Account(Long oauthId, String oauthPlatform) {
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
    }
}
