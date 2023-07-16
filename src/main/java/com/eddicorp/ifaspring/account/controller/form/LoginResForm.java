package com.eddicorp.ifaspring.account.controller.form;

import com.eddicorp.ifaspring.account.entity.Account;
import lombok.Getter;

@Getter
public class LoginResForm {
    private String userToken;
    private String nickName;
    private Long accountId;
    private String profileImgPath;

    public LoginResForm(String userToken, Account account) {
        this.userToken = userToken;
        this.nickName = account.getNickName();
        this.accountId = account.getId();
        this.profileImgPath = account.getProfileImage();
    }
}
