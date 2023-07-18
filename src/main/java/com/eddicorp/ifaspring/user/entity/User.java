package com.eddicorp.ifaspring.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
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

    public User(Long oauthId, String oauthPlatform, String profileImage) {
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
        this.profileImage = profileImage;
    }
}
