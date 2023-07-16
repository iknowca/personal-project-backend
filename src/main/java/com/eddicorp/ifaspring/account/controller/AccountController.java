package com.eddicorp.ifaspring.account.controller;

import com.eddicorp.ifaspring.account.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;

    @GetMapping("/check/nickname")
    public Boolean nicknameCheck(String nickname) {
        log.info(String.valueOf(accountService.nicknameCheck(nickname)));
        return accountService.nicknameCheck(nickname);
    }
    @GetMapping("/check/email")
    public Boolean emailCheck(String email) {
        return accountService.emailCheck(email);
    }
    @PostMapping("/additional-value")
    public Account setAdditionalValue(@RequestBody AdditionalValueReqForm reqForm, @RequestHeader HttpHeaders header) {
        log.info("setAccountValue()");
        System.out.println(header);
        return accountService.setAdditionalValue(reqForm, header);
    }
}
