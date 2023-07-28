package com.eddicorp.ifaspring.user.repository;

import com.eddicorp.ifaspring.user.entity.Follow;
import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("select f from Follow f where f.follower=:follower and f.followee=:followee")
    Optional<Follow> findByFollowerAndFollowee(User follower, User followee);
}
