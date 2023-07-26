package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.*;
import com.eddicorp.ifaspring.map.controller.form.LocationResForm;
import com.eddicorp.ifaspring.map.service.LocationService;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.service.UserService;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.BoardContent;
import com.eddicorp.ifaspring.board.entity.ImgPath;
import com.eddicorp.ifaspring.board.repository.BoardContentRepository;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import com.eddicorp.ifaspring.board.repository.ImgPathRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
    final UserService userService;
    final BoardRepository boardRepository;
    final BoardContentRepository boardContentRepository;
    final ImgPathRepository imgPathRepository;
    final LocationService locationService;

    @Override
    @Transactional
    public Long write(BoardReqForm reqForm) {
        User writer = userService.findByUserToken(reqForm.getUserToken());
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
                .location(reqForm.getLocation().toLocation())
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
        BoardContentResForm boardContentResForm = new BoardContentResForm(savedBoard.getContent());
        return BoardResForm.builder()
                .id(savedBoard.getId())
                .createdDate(savedBoard.getCreatedDate())
                .modifiedDate(savedBoard.getModifiedDate())
                .title(savedBoard.getTitle())
                .content(new BoardContentResForm(savedBoard.getContent()))
                .writer(new Writer(savedBoard.getWriter()))
                .replys(savedBoard.getReplys().stream().map(ReplyResForm::new).toList())
                .numReplys(savedBoard.getNumReply())
                .location(new LocationResForm(savedBoard.getLocation()))
                .build();
    }
//  @Override
//    public List<Board> requestBoardList() {
//        List<Board> boardList = boardRepository.findAllWithWriter();
//        return boardList;
//    }
    @Override
    public List<BoardResForm> requestBoardList() {
        List<BoardResForm> boardResFormList = boardRepository.findAllWithWriter().stream().map((b)->
                BoardResForm.builder()
                        .writer(new Writer(b.getWriter()))
                        .createdDate(b.getCreatedDate())
                        .title(b.getTitle())
                        .id(b.getId())
                        .build()
        ).toList();
        return boardResFormList;
    }
    @Override
    public List<BoardResForm> requestBoardList(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        List<BoardResForm> boardResFormList = boardRepository.findAllWithWriter(pageable).stream().map((b)->
                BoardResForm.builder()
                        .writer(new Writer(b.getWriter()))
                        .createdDate(b.getCreatedDate())
                        .title(b.getTitle())
                        .id(b.getId())
                        .numReplys(b.getNumReply())
                        .build()
        ).toList();
        return boardResFormList;
    }

    @Override
    @Transactional
    public Long modify(BoardReqForm reqForm) {
        User writer = userService.findByUserToken(reqForm.getUserToken());
        if(writer==null) {
            return null;
        }
        Optional<Board> maybeBoard = boardRepository.findById(reqForm.getBoardId());
        if(maybeBoard.isEmpty()) {
            return null;
        }
        Board savedBoard = maybeBoard.get();
        savedBoard.setModifiedDate(null);
        BoardContent savedBoardContent = savedBoard.getContent();

//        List<ImgPath> imgPathList = savedBoardContent.getImgPathList();

        savedBoard.setTitle(reqForm.getTitle());
        savedBoardContent.setStringContent(reqForm.getStringContent());

//        imgPathList = reqForm.getFiles().stream().map(ImgPath::new).map(imgPathRepository::save).toList();
//        savedBoardContent.setImgPathList(imgPathList);
        boardContentRepository.save(savedBoardContent);

        return boardRepository.save(savedBoard).getId();
    }

    @Override
    @Transactional
    public Boolean delete(Long boardId, HttpHeaders headers) {
        User writer = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if(writer==null) {
            return null;
        }
        Optional<Board> maybeBoard = boardRepository.findById(boardId);
        if(maybeBoard.isEmpty()) {
            return null;
        }
        Board savedBoard = maybeBoard.get();
        if(!Objects.equals(savedBoard.getWriter().getId(), writer.getId())) {
            return null;
        }

        List<ImgPath> imgPathList = savedBoard.getContent().getImgPathList();
        if(savedBoard.getContent().getImgPathList().size()!=0) {
            imgPathRepository.deleteAll(imgPathList);
        }
        boardContentRepository.delete(savedBoard.getContent());
        boardRepository.delete(savedBoard);

        return true;
    }

    @Override
    public List<BoardResForm> requestBoardListByAccountId(Long accountId) {
        List<BoardResForm> boardList = boardRepository.findAllByAccountId(accountId).stream().map((b)->
                BoardResForm.builder()
                        .writer(new Writer(b.getWriter()))
                        .createdDate(b.getCreatedDate())
                        .title(b.getTitle())
                        .id(b.getId())
                        .build()
        ).toList();
        return boardList;
    }

    @Override
    @Transactional
    public List<BoardResForm> requestBoardListByLocation(Map<String, String> location, HttpHeaders headers) {
        User user = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if(user==null) {
            return null;
        }
        System.out.println(locationService.getBoardByLocation(location));
        List<BoardResForm> boardList = locationService.getBoardByLocation(location).stream().map((b)->
                BoardResForm.builder()
                        .title(b.getTitle())
                        .id(b.getId())
                        .numReplys(b.getNumReply())
                        .writer(new Writer(b.getWriter()))
                        .createdDate(b.getCreatedDate())
                        .location(new LocationResForm(b.getLocation()))
                        .build()
                ).toList();
        log.info(boardList.toString());
        return boardList;
    }
}
