package com.eddicorp.ifaspring.board.controller.form;

import lombok.Getter;

import java.util.List;

@Getter
public class ForkResForm {
    public ForkResForm(Long boardId, List<Long> userIdList) {
        this.boardId = boardId;
        this.userIdList = userIdList;
    }

    private Long boardId;
    private List<Long> userIdList;
}
