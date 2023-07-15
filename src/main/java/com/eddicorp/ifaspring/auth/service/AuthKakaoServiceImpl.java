package com.eddicorp.ifaspring.auth.service;

import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.service.AccountService;
import com.eddicorp.ifaspring.auth.dto.KakaoProfile;
import com.eddicorp.ifaspring.auth.dto.OAuthToken;
import com.eddicorp.ifaspring.utility.property.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthKakaoServiceImpl implements AuthKakaoService{
    final private PropertyUtil propertyUtil;
    final private AccountService accountService;

    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("auth.kakao.client_id");
        final String REDIRECTION_URL = propertyUtil.getProperty("auth.redirection");
        final String url =  "https://kauth.kakao.com/oauth/authorize?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECTION_URL+"&response_type=code";

        return url;
    }

    @Override
    public LoginResForm getToken(String code) {
        final String url = "https://kauth.kakao.com/oauth/token";


        //HttpHeader setting
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", propertyUtil.getProperty("auth.kakao.client_id"));
        body.add("redirect_uri", propertyUtil.getProperty("auth.redirection"));
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OAuthToken> response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest, OAuthToken.class);

        LoginResForm resForm = getUserInfo(Objects.requireNonNull(response.getBody()).getAccess_token());

        return resForm;
    }

    @Override
    public LoginResForm getUserInfo(String accessToken){
        final String URL = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<HttpHeaders> kakaoUserInfoRequest = new HttpEntity<>(headers);
        ResponseEntity<KakaoProfile> kakaoUserInfoResponse = restTemplate.exchange(URL, HttpMethod.POST, kakaoUserInfoRequest, KakaoProfile.class);

        Long kakaoUserId = Objects.requireNonNull(kakaoUserInfoResponse.getBody()).getId();
        return accountService.loginOauthUser(kakaoUserId, "kakao");
    }


}
