package com.eddicorp.ifaspring.board.controller;

import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    final BoardService boardService;
    @PutMapping("/board")
    public Long write(@RequestBody BoardReqForm reqForm) {
        log.info("write()");
        return boardService.write(reqForm);
    }
    @GetMapping("/board/{boardId}")
    public BoardResForm requestBoard(@PathVariable Long boardId) {
        log.info("requestBoard: "+boardId);
        return boardService.requestBoard(boardId);
    }
}
