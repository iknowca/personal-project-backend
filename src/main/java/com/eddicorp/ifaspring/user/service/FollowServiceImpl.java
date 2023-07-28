package com.eddicorp.ifaspring.user.service;

import com.eddicorp.ifaspring.user.controller.form.FollowReqForm;
import com.eddicorp.ifaspring.user.controller.form.LoginResForm;
import com.eddicorp.ifaspring.user.entity.Follow;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.repository.FollowRepository;
import com.eddicorp.ifaspring.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowServiceImpl implements FollowService{
    final UserService userService;
    final UserRepository userRepository;
    final FollowRepository followRepository;

    @Override
    @Transactional
    public LoginResForm follow(FollowReqForm followReqForm, HttpHeaders headers) {
        User follower = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if(follower==null && !Objects.equals(follower.getId(), followReqForm.getFollowerUserId())) {
            return null;
        }
        Optional<User> follewee = userRepository.findById(followReqForm.getFolloweeUserId());
        if(follewee.isEmpty()) {
            return null;
        }
        Follow follow = new Follow(follower, follewee.get());
        followRepository.save(follow);
        follower.getFollowList().add(follow);
        userRepository.save(follower);

        return userService.login(follower);
    }

    @Override
    @Transactional
    public LoginResForm unFollow(Long followeeId, HttpHeaders headers) {
        User follower = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if(follower==null) {
            return null;
        }
        Optional<User> maybeFollowee = userRepository.findById(followeeId);
        if(maybeFollowee.isEmpty()) {
            return null;
        }
        Optional<Follow> maybeFollow = followRepository.findByFollowerAndFollowee(follower, maybeFollowee.get());
        if(maybeFollow.isEmpty()) {
            return null;
        }
        follower.getFollowList().remove(maybeFollow.get());
        userRepository.save(follower);
        followRepository.delete(maybeFollow.get());
        return userService.login(follower);
    }


}
