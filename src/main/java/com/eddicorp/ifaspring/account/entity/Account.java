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
    @Setter
    private String email;
    @Setter
    private String nickName;
    @JsonIgnore
    private Long oauthId;
    @JsonIgnore
    private String oauthPlatform;
    private String profileImage;

    public Account(Long oauthId, String oauthPlatform, String profileImage) {
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
        this.profileImage = profileImage;
    }
}
