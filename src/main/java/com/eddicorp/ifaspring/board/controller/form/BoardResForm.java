package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResForm {
    private Board board;
    private String writerName;
    private Long writerId;
    private String boardContent;

    @Builder
    public BoardResForm(Board board, Long writerId, String writerName, String boardContent) {
        this.board = board;
        this.writerId = writerId;
        this.writerName = writerName;
        this.boardContent = boardContent;
    }
}
