package com.eddicorp.ifaspring.user.controller;

import com.eddicorp.ifaspring.user.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping("/check/nickname")
    public Boolean nicknameCheck(String nickname) {
        log.info(String.valueOf(userService.nicknameCheck(nickname)));
        return userService.nicknameCheck(nickname);
    }
    @GetMapping("/check/email")
    public Boolean emailCheck(String email) {
        return userService.emailCheck(email);
    }
    @PostMapping("/additional-value")
    public User setAdditionalValue(@RequestBody AdditionalValueReqForm reqForm, @RequestHeader HttpHeaders header) {
        log.info("setAccountValue()");
        System.out.println(header);
        return userService.setAdditionalValue(reqForm, header);
    }
}
