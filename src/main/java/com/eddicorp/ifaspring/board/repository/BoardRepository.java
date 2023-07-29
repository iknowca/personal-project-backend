package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.map.entity.Location;
import com.eddicorp.ifaspring.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b join fetch b.writer")
    List<Board> findAllWithWriter();
    @Query("select b from Board b join fetch b.writer")
    List<Board> findAllWithWriter(Pageable pageable);
    @Query("select b from Board b " +
            "join fetch b.writer " +
            "join fetch b.content " +
            "join fetch b.location "+
            "left join fetch b.content.imgPathList " +
            "where b.id = :id")
    Optional<Board> findBoardByIdWithContent(Long id);
    @Query("select b from Board b join fetch b.writer w join fetch b.content where w.id=:accountId")
    List<Board> findAllByAccountId(Long accountId);
    @Query("select b from Board b join fetch b.writer " +
            "join fetch b.location " +
            "where b.location.d_o = :#{#locationCriteria['d_o']} " +
            "and b.location.si = :#{#locationCriteria['si']} " +
            "and b.location.gu = :#{#locationCriteria['gu']} " +
            "and b.location.dong = :#{#locationCriteria['dong']}")
    List<Board> findByLocationLevel1(@Param("locationCriteria") Map<String, String> locationCriteria, Pageable pageable);
    @Query("select b from Board b join fetch b.writer " +
            "join fetch b.location " +
            "where b.location.d_o = :#{#locationCriteria['d_o']} " +
            "and b.location.si = :#{#locationCriteria['si']} "+
            "and b.location.gu = :#{#locationCriteria['gu']} " )
    List<Board> findByLocationLevel2(@Param("locationCriteria") Map<String, String> locationCriteria, Pageable pageable);
    @Query("select b from Board b join fetch b.writer " +
            "join fetch b.location " +
            "where b.location.d_o = :#{#locationCriteria['d_o']} " +
            "and b.location.si = :#{#locationCriteria['si']} ")
    List<Board> findByLocationLevel3(@Param("locationCriteria") Map<String, String> locationCriteria, Pageable pageable);
    @Query("select b from Board b join fetch b.writer " +
            "join fetch b.location " +
            "where b.location.d_o = :#{#locationCriteria['d_o']}")
    List<Board> findByLocationLevel4(@Param("locationCriteria") Map<String, String> locationCriteria, Pageable pageable);
    @Query("select b from Board b join fetch b.writer ")
    List<Board> findByLocationLevel5(@Param("locationCriteria") Map<String, String> locationCriteria, Pageable pageable);

    @Query("select b from Board b " +
            "join fetch b.writer " +
            "join fetch b.content " +
            "join fetch b.location "+
            "left join fetch b.content.imgPathList " +
            "where b.forkUserList = :user")
    List<Board> findByFork(User user);
}