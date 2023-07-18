package com.eddicorp.ifaspring.user.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SignUpReqForm {

    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String nickName;
}
