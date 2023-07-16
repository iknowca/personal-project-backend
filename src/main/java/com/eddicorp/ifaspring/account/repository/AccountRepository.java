package com.eddicorp.ifaspring.account.repository;

import com.eddicorp.ifaspring.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByNickName(String nickName);

    @Query("select a from Account a where a.oauthId=:kakaoId and a.oauthPlatform=:platformName ")
    Optional<Account> findByOauthId(Long kakaoId, String platformName);
    Boolean existsAccountByNickName(String nickname);
    Boolean existsAccountByEmail(String email);

}
