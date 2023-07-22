package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.entity.ImgPath;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class BoardContentResForm {
    private String stringContent;
    private List<ImgPath> imgPathList;

    public BoardContentResForm(BoardContent boardContent) {
        this.stringContent = boardContent.getStringContent();
        this.imgPathList = boardContent.getImgPathList();
    }
}
