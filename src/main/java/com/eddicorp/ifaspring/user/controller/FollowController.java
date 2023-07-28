package com.eddicorp.ifaspring.user.controller;

import com.eddicorp.ifaspring.user.controller.form.FollowReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.service.FollowService;
import com.eddicorp.ifaspring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/follow")
public class FollowController {
    final FollowService followService;
    @PostMapping
    public LoginResForm follow(@RequestBody FollowReqForm followReqForm, @RequestHeader HttpHeaders headers) {
        log.info("follow()");
        return followService.follow(followReqForm, headers);
    }
    @DeleteMapping
    public LoginResForm unFollow(@RequestParam Long followeeId, @RequestHeader HttpHeaders headers) {
        log.info("unFollow()");
        return followService.unFollow(followeeId, headers);
    }
}
