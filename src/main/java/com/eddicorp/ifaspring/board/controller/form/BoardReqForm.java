package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.map.controller.form.LocationResForm;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardReqForm {
    private Long boardId;
    private String userToken;
    private String title;
    private String stringContent;
    private List<String> files;
    @JsonProperty(value="currentArea")
    private LocationResForm location;
}
