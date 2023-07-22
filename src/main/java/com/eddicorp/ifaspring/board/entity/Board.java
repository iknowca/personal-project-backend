package com.eddicorp.ifaspring.board.entity;

import com.eddicorp.ifaspring.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Builder
    public Board(String title, User writer, BoardContent content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
