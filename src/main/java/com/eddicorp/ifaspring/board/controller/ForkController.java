package com.eddicorp.ifaspring.board.controller;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.service.ForkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/fork")
public class ForkController {
    final ForkService forkService;
    @PostMapping("/{boardId}")
    public BoardResForm fork(@PathVariable Long boardId, @RequestHeader HttpHeaders headers) {
      log.info("fork()");
      return forkService.fork(boardId, headers);
    }
    @DeleteMapping("/{boardId}")
    public BoardResForm cancelFork(@PathVariable Long boardId, @RequestHeader HttpHeaders headers) {
        log.info("fork cancel()");
        return forkService.cancelFork(boardId, headers);
    }
    @GetMapping("/list")
    public List<BoardResForm> getForkBoardList(@RequestHeader HttpHeaders headers) {
        log.info("get fork board list()");
        return forkService.getBoard(headers);
    }
}
