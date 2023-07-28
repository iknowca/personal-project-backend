package com.eddicorp.ifaspring.user.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowReqForm {
    private Long followerUserId;
    private Long followeeUserId;

}
