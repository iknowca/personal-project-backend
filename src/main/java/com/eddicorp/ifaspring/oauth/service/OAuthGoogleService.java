package com.eddicorp.ifaspring.oauth.service;

import com.eddicorp.ifaspring.oauth.dto.GoogleProfile;
import com.eddicorp.ifaspring.oauth.dto.OAuthGoogleToken;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;

public interface OAuthGoogleService {
    public String getAuthorizeCode();
    public LoginResForm login(String code);

    OAuthGoogleToken getToken(String code);

    GoogleProfile getProfile(OAuthGoogleToken googleToken);
}
