package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b join fetch b.writer")
    List<Board> findAllWithWriter();
    @Query("select b from Board b join fetch b.writer join fetch b.content join fetch b.content.imgPathList where b.id = :id")
    Optional<Board> findBoardByIdWithContent(Long id);
}