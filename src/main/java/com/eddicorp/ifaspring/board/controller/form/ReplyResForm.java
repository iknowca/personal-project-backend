package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.Reply;
import com.eddicorp.ifaspring.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
public class ReplyResForm {
    private Long id;
    private Writer writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long boardId;

    @Builder
    public ReplyResForm(Reply reply) {
        this.id = reply.getId();
        this.writer = new Writer(reply.getWriter());
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
        this.boardId = reply.getBoard().getId();
    }
}
