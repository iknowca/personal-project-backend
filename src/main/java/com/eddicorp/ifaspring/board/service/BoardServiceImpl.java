package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.service.AccountService;
import com.eddicorp.ifaspring.board.controller.form.BoardReqForm;
import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.entity.ImgPath;
import com.eddicorp.ifaspring.board.repository.BoardContentRepository;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import com.eddicorp.ifaspring.board.repository.ImgPathRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
    final AccountService accountService;
    final BoardRepository boardRepository;
    final BoardContentRepository boardContentRepository;
    final ImgPathRepository imgPathRepository;

    @Override
    @Transactional
    public Long write(BoardReqForm reqForm) {
        Account writer = accountService.findByUserToken(reqForm.getUserToken());
        if(writer==null) {
            return null;
        }
        List<ImgPath> imgPathList = new ArrayList<>(reqForm.getFiles().stream().map(ImgPath::new).map(imgPathRepository::save).toList());
        BoardContent boardContent = new BoardContent(reqForm.getStringContent(), imgPathList);
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
        BoardContent boardContent = savedBoard.getContent();
        List<Long> imgPathList = boardContent.getImgPathList().stream().map((imgPath)->imgPath.getId()).toList();
        BoardResForm resForm = new BoardResForm(savedBoard, boardContent);
        return resForm;
    }

    @Override
    public List<Board> requestBoardList() {
        List<Board> boardList = boardRepository.findAllWithWriter();
        return boardList;
    }
}
