package com.eddicorp.ifaspring.board.controller;

import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.controller.form.ReplyReqForm;
import com.eddicorp.ifaspring.board.service.BoardService;
import com.eddicorp.ifaspring.board.service.ReplyService;
import com.eddicorp.ifaspring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/board/reply")
@Slf4j
@RequiredArgsConstructor
public class ReplyController {
    final ReplyService replyService;

    @PostMapping("/{boardId}")
    public BoardResForm write(@PathVariable Long boardId, @RequestHeader HttpHeaders headers, @RequestBody ReplyReqForm reqForm) {
        log.info("write()");
        return replyService.write(boardId, Objects.requireNonNull(headers.get("authorization")).get(0), reqForm);
    }
    @DeleteMapping
    public BoardResForm delete(@RequestParam Long replyId, @RequestHeader HttpHeaders headers) {
        log.info("delete reply()");
        return replyService.delete(replyId, Objects.requireNonNull(headers.get("authorization")).get(0));
    }
    @PutMapping
    public BoardResForm modify(@RequestParam Long replyId, @RequestHeader HttpHeaders headers, @RequestBody ReplyReqForm reqForm) {
        log.info("modify reply()");
        return replyService.modify(replyId, Objects.requireNonNull(headers.get("authorization").get(0)), reqForm);
    }
}
