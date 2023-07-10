package com.eddicorp.ifaspring.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BoardContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stringContent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ImgPath> imgPathList;

    public BoardContent(String stringContent, List<ImgPath> imgPathList) {
        this.stringContent = stringContent;
        this.imgPathList = imgPathList;
    }
}
