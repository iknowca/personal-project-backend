package com.eddicorp.ifaspring.account.repository;

import com.eddicorp.ifaspring.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByNickName(String nickName);

}
