package com.eddicorp.ifaspring.auth.controller;

import com.eddicorp.ifaspring.auth.service.AuthKakaoService;
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
public class AuthController {
    final AuthKakaoService kakaoService;

    @GetMapping("/kakao")
    public String requestKakaoAuthorizeCode() {
        log.info("kakao join()");
        return kakaoService.getAuthorizeCode();
    }
    @GetMapping("/kakao/oauth-code")
    public String getToken(@RequestParam String code) {
        log.info("kakao getToken()");
        return kakaoService.getToken(code);
    }
}
