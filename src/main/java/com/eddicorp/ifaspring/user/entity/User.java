package com.eddicorp.ifaspring.user.entity;

import com.eddicorp.ifaspring.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigInteger;
import java.util.List;

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
    private BigInteger oauthId;
    @JsonIgnore
    private String oauthPlatform;
    private String profileImage;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    List<Board> boardLiked;


    public User(BigInteger oauthId, String oauthPlatform, String profileImage) {
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
        this.profileImage = profileImage;
    }
}
