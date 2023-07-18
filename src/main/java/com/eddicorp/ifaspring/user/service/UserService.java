package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.http.HttpHeaders;

public interface UserService {

    User findByUserToken(String userToken);
    LoginResForm loginOauthUser(Long platformId, String platformName, String profileImage);
    User joinOauthUser(Long platformId, String platformName, String profileImage);

    Boolean nicknameCheck(String nickname);

    Boolean emailCheck(String email);

    User setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header);
}
