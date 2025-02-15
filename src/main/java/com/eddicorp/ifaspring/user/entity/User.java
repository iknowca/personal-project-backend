package com.eddicorp.ifaspring.user.entity;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.Fork;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String email;
    @Setter
    private String nickName;
    private BigInteger oauthId;
    private String oauthPlatform;
    private String profileImage;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Fork> forkList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "follower")
    private List<Follow> followList;


    public User(BigInteger oauthId, String oauthPlatform, String profileImage) {
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
        this.profileImage = profileImage;
    }
}
