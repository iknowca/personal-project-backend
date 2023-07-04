package com.eddicorp.ifaspring.account.service;

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
        return new LoginResForm(userToken);
    }
}
