package com.eddicorp.ifaspring.auth.service;

public interface AuthKakaoService {
    public String getAuthorizeCode();

    String getToken(String code);


    Long getUserInfo(String accessToken);
}
