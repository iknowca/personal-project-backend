package com.eddicorp.ifaspring.board.entity;

import com.eddicorp.ifaspring.map.entity.Location;
import com.eddicorp.ifaspring.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch=FetchType.LAZY)
    private User writer;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    private BoardContent content;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<Reply> replys;
    @Formula("(select count(1) from reply r where r.board_id = id)")
    private Integer numReply;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Location location;

    @Builder
    public Board(String title, User writer, BoardContent content, Location location) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.numReply = 0;
        this.location = location;
    }
}
