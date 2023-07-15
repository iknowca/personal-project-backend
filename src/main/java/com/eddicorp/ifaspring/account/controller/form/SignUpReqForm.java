package com.eddicorp.ifaspring.account.controller.form;

import com.eddicorp.ifaspring.account.entity.Account;
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
