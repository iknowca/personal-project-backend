package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.controller.form.ReplyReqForm;

public interface ReplyService {
    BoardResForm write(Long boardId, String authorization, ReplyReqForm reqForm);

    BoardResForm delete(Long replyId, String authorization);

    BoardResForm modify(Long replyId, String authorization, ReplyReqForm reqForm);
}
