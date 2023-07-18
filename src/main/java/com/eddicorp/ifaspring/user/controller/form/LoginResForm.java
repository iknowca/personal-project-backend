package com.eddicorp.ifaspring.user.controller.form;

import com.eddicorp.ifaspring.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResForm {
    private String userToken;
    private String nickName;
    private Long accountId;
    private String profileImgPath;

    public LoginResForm(String userToken, User user) {
        this.userToken = userToken;
        this.nickName = user.getNickName();
        this.accountId = user.getId();
        this.profileImgPath = user.getProfileImage();
    }
}
