package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
