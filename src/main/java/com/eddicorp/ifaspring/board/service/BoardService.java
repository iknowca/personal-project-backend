package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface BoardService {
    Long write(BoardReqForm reqForm);

    BoardResForm requestBoard(Long boardId);
    List<BoardResForm> requestBoardList(Integer page, Integer size);


    List<BoardResForm> requestBoardList();

    Long modify(BoardReqForm reqForm);

    Boolean delete(Long boardId, HttpHeaders headers);

    List<BoardResForm> requestBoardListByAccountId(Long accountId);

}
