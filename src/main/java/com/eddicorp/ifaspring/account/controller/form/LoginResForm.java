package com.eddicorp.ifaspring.account.controller.form;

import lombok.Getter;

@Getter
public class LoginResForm {
    private String userToken;
    private String nickName;
    private Long accountId;

    public LoginResForm(String userToken, String nickName, Long accountId) {
        this.userToken = userToken;
        this.nickName = nickName;
        this.accountId = accountId;
    }
}
