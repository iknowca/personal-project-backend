package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;

import java.util.List;

public interface BoardService {
    Long write(BoardReqForm reqForm);

    BoardResForm requestBoard(Long boardId);

    List<Board> requestBoardList();
}
