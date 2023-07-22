package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResForm {
    private Long id;
    private String title;
    private User writer;
    private BoardContent content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardResForm(Long id, String title, User writer, BoardContent content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
