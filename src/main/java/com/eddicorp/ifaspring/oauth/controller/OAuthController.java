package com.eddicorp.ifaspring.oauth.controller;

import com.eddicorp.ifaspring.oauth.service.OAuthGoogleService;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.oauth.service.OAuthKakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuthController {
    final OAuthKakaoService kakaoService;
    final OAuthGoogleService googleService;

    @GetMapping("/kakao")
    public String requestKakaoAuthorizeCode() {
        log.info("kakao join()");
        return kakaoService.getAuthorizeCode();
    }
    @GetMapping("/kakao/oauth-code")
    public LoginResForm getToken(@RequestParam String code) {
        log.info("kakao getToken()");
        return kakaoService.getToken(code);
    }

    @GetMapping("/google")
    public String requestGoogleAuthorizeCode() {
        log.info("google join()");
        return googleService.getAuthorizeCode();
    }

    @GetMapping("/google/access-token")
    public LoginResForm getGoogleToken(@RequestParam String code) {
        log.info("google getToken()");
        return googleService.login(code);
    }

}
