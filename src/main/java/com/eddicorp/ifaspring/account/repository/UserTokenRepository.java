package com.eddicorp.ifaspring.account.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserTokenRepository {
    final Map<String, Long> userTokenMap = new HashMap<>();

    public String setUserToken(Long id) {
        String userToken = UUID.randomUUID().toString();
        userTokenMap.put(userToken, id);
        return userToken;
    }
    public Long findByUserToken(String userToken) {
        return userTokenMap.get(userToken);
    }
}
