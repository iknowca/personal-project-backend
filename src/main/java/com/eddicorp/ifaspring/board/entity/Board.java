package com.eddicorp.ifaspring.board.entity;

import com.eddicorp.ifaspring.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Reply> replys;
    private Integer numReply;

    @Builder
    public Board(String title, User writer, BoardContent content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.numReply = 0;
    }
}
