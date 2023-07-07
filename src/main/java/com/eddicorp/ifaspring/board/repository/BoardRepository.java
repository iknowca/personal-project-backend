package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
