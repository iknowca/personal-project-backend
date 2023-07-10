package com.eddicorp.ifaspring.board.controller.form;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardReqForm {
    private String userToken;
    private String title;
    private String stringContent;
    private List<String> files;

}
