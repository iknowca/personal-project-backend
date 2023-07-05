package com.eddicorp.ifaspring.account.service;

import com.eddicorp.ifaspring.account.controller.form.LoginReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.controller.form.SignUpReqForm;
import com.eddicorp.ifaspring.account.entity.Account;

public interface AccountService {
    LoginResForm signUp(SignUpReqForm reqForm);

    LoginResForm logIn(LoginReqForm reqForm);

    Account findByUserToken(String userToken);
}
