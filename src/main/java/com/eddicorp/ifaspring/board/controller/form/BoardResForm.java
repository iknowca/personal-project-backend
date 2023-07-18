package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BoardResForm {
    private Board board;
    private BoardContent boardContent;

    @Builder
    public BoardResForm(Board board, BoardContent boardContent) {
        this.board = board;
        this.boardContent = boardContent;
    }
}
