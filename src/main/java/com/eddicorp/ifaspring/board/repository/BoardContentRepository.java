package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.BoardContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardContentRepository extends JpaRepository<BoardContent, Long> {
}
