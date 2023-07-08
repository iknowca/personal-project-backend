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
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BoardResForm {
    private Board board;
    private String boardContent;

    @Builder
    public BoardResForm(Board board, String boardContent) {
        this.board = board;
        this.boardContent = boardContent;
    }
}
