package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.entity.Fork;
import com.eddicorp.ifaspring.board.entity.Reply;
import com.eddicorp.ifaspring.map.controller.form.LocationResForm;
import com.eddicorp.ifaspring.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
    private ForkResForm forkUserList ;
}
