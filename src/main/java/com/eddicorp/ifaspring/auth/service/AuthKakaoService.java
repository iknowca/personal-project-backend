package com.eddicorp.ifaspring.auth.service;

import com.eddicorp.ifaspring.account.controller.form.LoginResForm;

public interface AuthKakaoService {
    public String getAuthorizeCode();

    LoginResForm getToken(String code);


    LoginResForm getUserInfo(String accessToken);
}
