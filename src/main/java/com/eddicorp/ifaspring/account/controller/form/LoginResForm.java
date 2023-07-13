package com.eddicorp.ifaspring.account.controller.form;

import lombok.Getter;

@Getter
public class LoginResForm {
    private String userToken;
    private String nickName;

    public LoginResForm(String userToken, String nickName) {
        this.userToken = userToken;
        this.nickName = nickName;
    }
}
