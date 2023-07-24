package com.eddicorp.ifaspring.user.service;

public interface UserTokenService {

    void setKeyAndValue(String token, Long accountId);
    Long getValueByKey(String token);
    void deleteByKey(String token);
}
