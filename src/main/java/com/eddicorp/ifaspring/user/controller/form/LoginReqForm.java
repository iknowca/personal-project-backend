package com.eddicorp.ifaspring.user.controller.form;

import lombok.Getter;

public class LoginReqForm {
    public LoginReqForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Getter
    private String email;
    @Getter
    private String password;
}
