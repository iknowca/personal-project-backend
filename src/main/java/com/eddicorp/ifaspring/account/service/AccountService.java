package com.eddicorp.ifaspring.account.service;

import com.eddicorp.ifaspring.account.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.entity.Account;
import org.springframework.http.HttpHeaders;

public interface AccountService {

    Account findByUserToken(String userToken);
    LoginResForm loginOauthUser(Long platformId, String platformName, String profileImage);
    Account joinOauthUser(Long platformId, String platformName, String profileImage);

    Boolean nicknameCheck(String nickname);

    Boolean emailCheck(String email);

    Account setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header);
}
