package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.controller.form.UserResForm;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.repository.UserRepository;
import com.eddicorp.ifaspring.user.repository.UserTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    public LoginResForm loginOauthUser(BigInteger platformId, String platformName, String profileImage) {
        Optional<User> maybeUser = userRepository.findByOauthId(platformId, platformName);

        User savedUser = maybeUser.orElseGet(() -> joinOauthUser(platformId, platformName, profileImage));
        String userToken = userTokenRepository.setUserToken(savedUser.getId());
        return new LoginResForm(userToken, savedUser);
    }

    @Override
    public User joinOauthUser(BigInteger platformId, String platformName, String profileImage) {
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
    @Transactional
    public UserResForm setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header) {
        User maybeUser = findByUserToken(Objects.requireNonNull(header.get("authorization")).get(0));
        if(maybeUser ==null) {
            return null;
        }
        maybeUser.setEmail(reqForm.getEmail());
        maybeUser.setNickName(reqForm.getNickName());
        User savedUser = userRepository.save(maybeUser);
        return UserResForm.builder()
                .id(savedUser.getId())
                .nickName(savedUser.getNickName())
                .profileImage(savedUser.getProfileImage())
                .email(savedUser.getEmail())
                .oauthPlatform(savedUser.getOauthPlatform())
                .build();
    }
}
