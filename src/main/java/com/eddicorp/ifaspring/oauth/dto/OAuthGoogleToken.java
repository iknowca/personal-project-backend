package com.eddicorp.ifaspring.oauth.dto;

import lombok.Data;

@Data
public class OAuthGoogleToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
}