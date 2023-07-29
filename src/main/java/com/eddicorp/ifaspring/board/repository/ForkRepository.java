package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.Fork;
import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ForkRepository extends JpaRepository<Fork, Long> {
    @Query("select f from Fork f where f.board = :board and f.user=:user")
    Optional<Fork> findByBoardAndUser(Board board, User user);

    @Query("select f from Fork f where f.board=:board")
    List<Fork> findAllByBoard(Board board);

    @Query("select distinct f.board from Fork f join fetch f.board.writer " +
            "join fetch f.board.location " +
            " where f.user = :user")
    List<Board> findAllByUser(User user);
}
