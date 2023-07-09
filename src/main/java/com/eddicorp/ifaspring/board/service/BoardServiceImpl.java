package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.service.AccountService;
import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.repository.BoardContentRepository;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
    final AccountService accountService;
    final BoardRepository boardRepository;
    final BoardContentRepository boardContentRepository;

    @Override
    public Long write(BoardReqForm reqForm) {
        Account writer = accountService.findByUserToken(reqForm.getUserToken());
        if(writer==null) {
            return null;
        }
        BoardContent boardContent = new BoardContent(reqForm.getStringContent());
        boardContentRepository.save(boardContent);
        Board board = Board.builder()
                .title(reqForm.getTitle())
                .content(boardContent)
                .writer(writer)
                .build();

        return boardRepository.save(board).getId();
    }

    @Override
    @Transactional
    public BoardResForm requestBoard(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findBoardByIdWithContent(boardId);
        if(maybeBoard.isEmpty()) {
            return null;
        }
        Board savedBoard = maybeBoard.get();
        new BoardResForm();
        BoardResForm resForm = new BoardResForm(savedBoard, savedBoard.getContent());
        log.info(String.valueOf(resForm));
        return resForm;
    }

    @Override
    public List<Board> requestBoardList() {
        List<Board> boardList = boardRepository.findAllWithWriter();
        return boardList;
    }
}
