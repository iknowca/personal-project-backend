package com.eddicorp.ifaspring.account.service;

import com.eddicorp.ifaspring.account.controller.form.LoginReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.controller.form.SignUpReqForm;
import com.eddicorp.ifaspring.account.entity.Account;

public interface AccountService {

    Account findByUserToken(String userToken);
    LoginResForm loginOauthUser(Long platformId, String platformName, String profileImage);
    Account joinOauthUser(Long platformId, String platformName, String profileImage);
}
