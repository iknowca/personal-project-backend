package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.service.AccountService;
import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.repository.BoardContentRepository;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
