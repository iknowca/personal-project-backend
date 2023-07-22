package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.controller.form.UserResForm;
import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.http.HttpHeaders;

import java.math.BigInteger;

public interface UserService {

    User findByUserToken(String userToken);
    LoginResForm loginOauthUser(BigInteger platformId, String platformName, String profileImage);
    User joinOauthUser(BigInteger platformId, String platformName, String profileImage);

    Boolean nicknameCheck(String nickname);

    Boolean emailCheck(String email);

    UserResForm setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header);
}
