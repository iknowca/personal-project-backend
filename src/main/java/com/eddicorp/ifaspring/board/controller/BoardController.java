package com.eddicorp.ifaspring.board.controller;

import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    final BoardService boardService;
    @PostMapping
    public Long write(@RequestBody BoardReqForm reqForm) {
        log.info("write()");
        log.info(reqForm.getFiles().toString());
        return boardService.write(reqForm);
    }
    @GetMapping("/{boardId}")
    public BoardResForm requestBoard(@PathVariable Long boardId) {
        log.info("requestBoard: "+boardId);
        return boardService.requestBoard(boardId);
    }

    @GetMapping("/list")
    public List<BoardResForm> requestBoardList() {
        log.info("requestBoardList");
        return boardService.requestBoardList();
    }
    @GetMapping(value="/list", params={"page", "size"})
    public List<BoardResForm> requestBoardList(@RequestParam Integer page, @RequestParam Integer size) {
        log.info("requestBoardList with pagination()");
        return boardService.requestBoardList(page, size);
    }

    @PutMapping
    public Long modify(@RequestBody BoardReqForm reqForm) {
        log.info("modify()");
        return boardService.modify(reqForm);
    }
    @DeleteMapping()
    public Boolean delete(@RequestParam Long boardId, @RequestHeader HttpHeaders headers) {
        return boardService.delete(boardId, headers);
    }
    @GetMapping("/list/{accountId}")
    public List<BoardResForm> requestBoardListByAccountId(@PathVariable Long accountId) {
        log.info("requestBoardListByAccountId");
        return boardService.requestBoardListByAccountId(accountId);
    }

    @GetMapping(value="/list", params={"d_o", "si", "gu", "dong", "lat", "lng", "level"})
    public List<BoardResForm> requestBoardListByLocation(@RequestParam Map<String, String> location, @RequestHeader HttpHeaders headers) {
        log.info("requestBoardListbyLocation()");
        return boardService.requestBoardListByLocation(location, headers);
    }
    @GetMapping(value = "/follow/list")
    public List<BoardResForm> requestBoardListByFollow(@RequestHeader HttpHeaders headers) {
        log.info("requestBoardListByFollow()");
        return boardService.requestBoardListByFollow(headers);
    }
}
