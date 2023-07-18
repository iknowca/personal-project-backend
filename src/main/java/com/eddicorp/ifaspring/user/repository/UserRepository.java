package com.eddicorp.ifaspring.user.repository;

import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickName(String nickName);

    @Query("select a from User a where a.oauthId=:kakaoId and a.oauthPlatform=:platformName ")
    Optional<User> findByOauthId(Long kakaoId, String platformName);
    Boolean existsUserByNickName(String nickname);
    Boolean existsUserByEmail(String email);

}
