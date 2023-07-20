package com.eddicorp.ifaspring.oauth.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class GoogleProfile {
    private BigInteger id;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;

}
