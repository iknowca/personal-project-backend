package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.entity.Reply;
import com.eddicorp.ifaspring.map.controller.form.LocationResForm;
import com.eddicorp.ifaspring.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardResForm {
    private Long id;
    private String title;
    private Writer writer;
    private BoardContentResForm content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<ReplyResForm> replys;
    private Integer numReplys;
    private LocationResForm location;

    @Builder
    public BoardResForm(Long id, String title, Writer writer, BoardContentResForm content, LocalDateTime createdDate, LocalDateTime modifiedDate, List<ReplyResForm> replys, Integer numReplys, LocationResForm location) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.replys = replys;
        this.numReplys = numReplys;
        this.location = location;
    }

}
