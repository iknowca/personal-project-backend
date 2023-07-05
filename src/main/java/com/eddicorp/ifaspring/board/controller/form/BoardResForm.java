package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import lombok.Getter;

@Getter
public class BoardResForm {
    private Board board;
    private BoardContent boardContent;
    private Account writer;

    public BoardResForm(Board board) {
        this.board = board;
    }
}
