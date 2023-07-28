package com.eddicorp.ifaspring.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow {

    public Follow(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private User follower;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="followee_id")
    private User followee;
}
