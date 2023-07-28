package com.eddicorp.ifaspring.board.entity;

import com.eddicorp.ifaspring.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Fork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    public Fork(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
