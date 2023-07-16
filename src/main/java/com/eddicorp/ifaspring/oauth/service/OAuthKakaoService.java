package com.eddicorp.ifaspring.oauth.service;

import com.eddicorp.ifaspring.account.controller.form.LoginResForm;

public interface OAuthKakaoService {
    public String getAuthorizeCode();

    LoginResForm getToken(String code);


    LoginResForm getUserInfo(String accessToken);
}
