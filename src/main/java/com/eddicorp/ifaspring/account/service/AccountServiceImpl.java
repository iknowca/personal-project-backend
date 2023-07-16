package com.eddicorp.ifaspring.account.service;

import com.eddicorp.ifaspring.account.controller.form.AdditionalValueReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.repository.AccountRepository;
import com.eddicorp.ifaspring.account.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    final AccountRepository accountRepository;
    final UserTokenRepository userTokenRepository;

    @Override
    public Account findByUserToken(String userToken) {
        Long userId = userTokenRepository.findByUserToken(userToken);
        if(userId==null) {
            log.info("no account has received userToken");
            return null;
        }
        Optional<Account> maybeAccount = accountRepository.findById(userId);
        if(maybeAccount.isEmpty()) {
            log.info("no account has fined userId");
            return null;
        }
        return maybeAccount.get();
    }
    @Override
    public LoginResForm loginOauthUser(Long platformId, String platformName, String profileImage) {
        Optional<Account> maybeAccount = accountRepository.findByOauthId(platformId, "kakao");

        Account savedAccount = maybeAccount.orElseGet(() -> joinOauthUser(platformId, platformName, profileImage));
        String userToken = userTokenRepository.setUserToken(savedAccount.getId());
        return new LoginResForm(userToken, savedAccount);
    }

    @Override
    public Account joinOauthUser(Long platformId, String platformName, String profileImage) {
        Account newAccount =  new Account(platformId, platformName, profileImage);
        return accountRepository.save(newAccount);
    }

    @Override
    public Boolean nicknameCheck(String nickname) {
        return accountRepository.existsAccountByNickName(nickname);
    }

    @Override
    public Boolean emailCheck(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    @Override
    public Account setAdditionalValue(AdditionalValueReqForm reqForm, HttpHeaders header) {
        Account maybeAccount = findByUserToken(Objects.requireNonNull(header.get("authorization")).get(0));
        if(maybeAccount==null) {
            return null;
        }
        maybeAccount.setEmail(reqForm.getEmail());
        maybeAccount.setNickName(reqForm.getNickName());

        return accountRepository.save(maybeAccount);
    }
}
