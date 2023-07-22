package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Writer {
    private Long id;
    private String nickName;
    private String profileImage;

    public Writer(User user) {
        this.id = user.getId();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
    }
}
