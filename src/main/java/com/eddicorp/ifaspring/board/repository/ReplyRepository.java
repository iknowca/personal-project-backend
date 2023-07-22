package com.eddicorp.ifaspring.board.repository;

import com.eddicorp.ifaspring.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
