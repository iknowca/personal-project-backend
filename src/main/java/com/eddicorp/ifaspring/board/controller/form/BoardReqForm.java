package com.eddicorp.ifaspring.board.controller.form;

import lombok.Getter;

@Getter
public class BoardReqForm {
    private String userToken;
    private String title;
    private String stringContent;

}
