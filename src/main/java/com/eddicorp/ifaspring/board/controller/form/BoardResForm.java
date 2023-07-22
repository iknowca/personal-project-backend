package com.eddicorp.ifaspring.board.controller.form;

import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResForm {
    private Long id;
    private String title;
    private Writer writer;
    private BoardContent content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardResForm(Long id, String title, User writer, BoardContent content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.writer = new Writer(writer);
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @Data
    @NoArgsConstructor
    public class Writer {
        private Long id;
        private String nickName;
        private String profileImage;

        public Writer(User user) {
            this.id = user.getId();
            this.nickName = user.getNickName();
            this.profileImage = user.getProfileImage();
        }
    }
}
