package com.eddicorp.ifaspring.user.controller.form;

import com.eddicorp.ifaspring.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResForm {
    private Long id;
    private String email;
    private String nickName;
    private BigInteger oauthId;
    private String oauthPlatform;
    private String profileImage;
    List<Board> boardLiked;

    @Builder
    public UserResForm(Long id, String email, String nickName, BigInteger oauthId, String oauthPlatform, String profileImage, List<Board> boardLiked) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.oauthId = oauthId;
        this.oauthPlatform = oauthPlatform;
        this.profileImage = profileImage;
        this.boardLiked = boardLiked;
    }
}
