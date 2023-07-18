package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.repository.UserRepository;
import com.eddicorp.ifaspring.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserTokenRepository userTokenRepository;

    @Override
    public User findByUserToken(String userToken) {
        Long userId = userTokenRepository.findByUserToken(userToken);
        if(userId==null) {
            log.info("no User has received userToken");
            return null;
        }
        Optional<User> maybeUser = userRepository.findById(userId);
        if(maybeUser.isEmpty()) {
            log.info("no User has fined userId");
            return null;
        }
        return maybeUser.get();
    }
    @Override
    public LoginResForm loginOauthUser(Long platformId, String platformName, String profileImage) {
        Optional<User> maybeUser = userRepository.findByOauthId(platformId, "kakao");

        User savedUser = maybeUser.orElseGet(() -> joinOauthUser(platformId, platformName, profileImage));
        String userToken = userTokenRepository.setUserToken(savedUser.getId());
        return new LoginResForm(userToken, savedUser);
    }

    @Override
    public User joinOauthUser(Long platformId, String platformName, String profileImage) {
        User newUser =  new User(platformId, platformName, profileImage);
        return userRepository.save(newUser);
    }

    @Override
    public Boolean nicknameCheck(String nickname) {
        return userRepository.existsUserByNickName(nickname);
    }

    @Override
    public Boolean emailCheck(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header) {
        User maybeUser = findByUserToken(Objects.requireNonNull(header.get("authorization")).get(0));
        if(maybeUser ==null) {
            return null;
        }
        maybeUser.setEmail(reqForm.getEmail());
        maybeUser.setNickName(reqForm.getNickName());

        return userRepository.save(maybeUser);
    }
}
