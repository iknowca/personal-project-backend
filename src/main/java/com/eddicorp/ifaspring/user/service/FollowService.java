package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.FollowReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import org.springframework.http.HttpHeaders;

public interface FollowService {
    LoginResForm follow(FollowReqForm followReqForm, HttpHeaders headers);

    LoginResForm unFollow(Long followeeId, HttpHeaders headers);
}
