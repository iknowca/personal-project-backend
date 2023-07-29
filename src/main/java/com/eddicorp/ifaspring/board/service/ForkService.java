package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface ForkService {
    BoardResForm fork(Long boardId, HttpHeaders headers);

    BoardResForm cancelFork(Long boardId, HttpHeaders headers);

    List<BoardResForm> getBoard(HttpHeaders headers);
}
