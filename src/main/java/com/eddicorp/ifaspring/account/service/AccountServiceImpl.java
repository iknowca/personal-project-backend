package com.eddicorp.ifaspring.account.service;

import com.eddicorp.ifaspring.account.controller.form.LoginReqForm;
import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.controller.form.SignUpReqForm;
import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.repository.AccountRepository;
import com.eddicorp.ifaspring.account.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    final AccountRepository accountRepository;
    final UserTokenRepository userTokenRepository;
    @Override
    public LoginResForm signUp(SignUpReqForm reqForm) {
        Optional<Account> maybeAccount = accountRepository.findByEmail(reqForm.getEmail());
        if(maybeAccount.isPresent()) {
            log.info("duplicate email");
            return null;
        }
        maybeAccount = accountRepository.findByNickName(reqForm.getNickName());
        if(maybeAccount.isPresent()) {
            log.info("duplicate nickName");
            return null;
        }
        Account createdAccount = reqForm.toAccount();
        accountRepository.save(createdAccount);
        String userToken = userTokenRepository.setUserToken(createdAccount.getId());
        return new LoginResForm(userToken, createdAccount.getNickName());
    }

    @Override
    public LoginResForm logIn(LoginReqForm reqForm) {
        Optional<Account> maybeAccount = accountRepository.findByEmail(reqForm.getEmail());
        if (maybeAccount.isEmpty()) {
            log.info("no account has received email");
            return null;
        }

        Account savedAccount = maybeAccount.get();
        if (!savedAccount.getPassword().equals(reqForm.getPassword())) {
            log.info("wrong password");
            return null;
        }
        String userToken = userTokenRepository.setUserToken(savedAccount.getId());
        return new LoginResForm(userToken, savedAccount.getNickName());
    }

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
}
