package com.eddicorp.ifaspring.board.entity;

import com.eddicorp.ifaspring.account.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch=FetchType.LAZY)
    private Account writer;
    @OneToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private BoardContent content;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Builder
    public Board(String title, Account writer, BoardContent content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
