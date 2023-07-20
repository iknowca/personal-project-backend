package com.eddicorp.ifaspring.oauth.service;

import com.eddicorp.ifaspring.oauth.dto.GoogleProfile;
import com.eddicorp.ifaspring.oauth.dto.OAuthGoogleToken;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.service.UserService;
import com.eddicorp.ifaspring.utility.property.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthGoogleServiceImpl implements OAuthGoogleService{
    final PropertyUtil propertyUtil;
    final UserService userService;

    @Override
    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("auth.google.client_id");
        final String REDIRECTION_URL = propertyUtil.getProperty("auth.google.redirection");
        final String url =  "https://accounts.google.com/o/oauth2/v2/auth?client_id="
                +CLIENT_ID+"&redirect_uri="+REDIRECTION_URL+"&response_type=code"
                +"&scope=https://www.googleapis.com/auth/userinfo.profile";

        return url;

    }

    @Override
    public LoginResForm login(String code) {
        OAuthGoogleToken googleToken= getToken(code);
        GoogleProfile googleProfile = getProfile(googleToken);
        return userService.loginOauthUser(googleProfile.getId(), "google", googleProfile.getPicture());
//        return null;
    }

    @Override
    public OAuthGoogleToken getToken(String code) {
        String URL = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        //HttpHeader setting
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "oauth2.googleapis.com");
        headers.add("Content-type", "application/x-www-form-urlencoded");


        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", propertyUtil.getProperty("auth.google.client_id"));
        body.add("redirect_uri", propertyUtil.getProperty("auth.google.redirection"));
        body.add("code", code);
        body.add("client_secret", propertyUtil.getProperty("auth.google.secrets"));

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<OAuthGoogleToken> response = restTemplate.exchange(URL, HttpMethod.POST, tokenRequest, OAuthGoogleToken.class);
        return response.getBody();
    }

    @Override
    public GoogleProfile getProfile(OAuthGoogleToken googleToken) {
        String URL = "https://www.googleapis.com/oauth2/v1/userinfo";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+googleToken.getAccess_token());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> infoRequest = new HttpEntity<>(body, headers);
        System.out.println(restTemplate.exchange(URL, HttpMethod.GET, infoRequest, String.class));
        ResponseEntity<GoogleProfile> googleProfileResponseEntity = restTemplate.exchange(URL, HttpMethod.GET, infoRequest, GoogleProfile.class);
        System.out.println(googleProfileResponseEntity);
        return googleProfileResponseEntity.getBody();
//        return null;
    }
}
