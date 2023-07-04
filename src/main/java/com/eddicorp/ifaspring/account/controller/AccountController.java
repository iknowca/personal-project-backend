package com.eddicorp.ifaspring.account.controller;

import com.eddicorp.ifaspring.account.controller.form.LoginReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.controller.form.SignUpReqForm;
import com.eddicorp.ifaspring.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;

    @PostMapping("/sign-up")
    public LoginResForm signUp(@RequestBody SignUpReqForm reqForm) {
        log.info("signUp");
        return accountService.signUp(reqForm);
    }

    @PostMapping("/log-in")
    public LoginResForm logIn(@RequestBody LoginReqForm reqForm) {
        log.info("login");
        return accountService.logIn(reqForm);
    }
}
