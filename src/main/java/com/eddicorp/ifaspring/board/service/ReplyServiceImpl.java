package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.controller.form.ReplyReqForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.Reply;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import com.eddicorp.ifaspring.board.repository.ReplyRepository;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    final ReplyRepository replyRepository;
    final BoardService boardService;
    final BoardRepository boardRepository;
    final UserService userService;
    @Override
    @Transactional
    public BoardResForm write(Long boardId, String authorization, ReplyReqForm reqForm) {
        Optional<Board> maybeBoard = boardRepository.findById(boardId);
        if(maybeBoard.isEmpty()) {
            return null;
        }
        User maybeUser = userService.findByUserToken(authorization);
        if(maybeUser==null) {
            return null;
        }
        Reply reply = Reply.builder()
                .writer(maybeUser)
                .content(reqForm.getContent())
                .board(maybeBoard.get())
                .build();
        replyRepository.save(reply);
        return boardService.requestBoard(boardId);
    }

    @Override
    @Transactional
    public BoardResForm delete(Long replyId, String authorization) {
        User maybeUser = userService.findByUserToken(authorization);
        if(maybeUser==null) {
            return null;
        }
        Optional<Reply> maybeReply = replyRepository.findById(replyId);
        if(maybeReply.isEmpty()) {
            return null;
        }
        Reply savedReply = maybeReply.get();
        if(savedReply.getWriter().getId()!= maybeUser.getId()) {
            return null;
        }
        Board savedBoard = savedReply.getBoard();
        savedBoard.getReplys().remove(savedReply);
        replyRepository.delete(savedReply);
        boardRepository.save(savedBoard);

        return boardService.requestBoard(savedBoard.getId());
    }

    @Override
    public BoardResForm modify(Long replyId, String authorization, ReplyReqForm reqForm) {
        User maybeUser = userService.findByUserToken(authorization);
        if(maybeUser==null) {
            return null;
        }
        Optional<Reply> maybeReply = replyRepository.findById(replyId);
        if(maybeReply.isEmpty()) {
            return null;
        }
        Reply savedReply = maybeReply.get();
        if(savedReply.getWriter().getId()!= maybeUser.getId()) {
            return null;
        }
        savedReply.setContent(reqForm.getContent());
        replyRepository.save(savedReply);
        Long boardId = savedReply.getBoard().getId();
        return boardService.requestBoard(boardId);
    }
}
