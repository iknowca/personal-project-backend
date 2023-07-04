package com.eddicorp.ifaspring.account.controller.form;

import lombok.Getter;

@Getter
public class LoginResForm {
    private String userToken;

    public LoginResForm(String userToken) {
        this.userToken = userToken;
    }
}
