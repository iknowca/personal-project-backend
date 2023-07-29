package com.eddicorp.ifaspring.board.service;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.controller.form.Writer;
import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.entity.Fork;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import com.eddicorp.ifaspring.board.repository.ForkRepository;
import com.eddicorp.ifaspring.map.controller.form.LocationResForm;
import com.eddicorp.ifaspring.user.entity.User;
import com.eddicorp.ifaspring.user.repository.UserRepository;
import com.eddicorp.ifaspring.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ForkServiceImpl implements ForkService {
    final UserService userService;
    final BoardService boardService;
    final BoardRepository boardRepository;
    final UserRepository userRepository;
    final ForkRepository forkRepository;

    @Override
    @Transactional
    public BoardResForm fork(Long boardId, HttpHeaders headers) {
        User user = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if (user == null) {
            return null;
        }
        Optional<Board> maybeBoard = boardRepository.findById(boardId);
        if (maybeBoard.isEmpty()) {
            return null;
        }

        Board board = maybeBoard.get();
        Fork fork = new Fork(board, user);
        forkRepository.save(fork);

        return boardService.requestBoard(boardId);
    }

    @Override
    @Transactional
    public BoardResForm cancelFork(Long boardId, HttpHeaders headers) {
        User user = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if (user == null) {
            return null;
        }
        Optional<Board> maybeBoard = boardRepository.findById(boardId);
        if (maybeBoard.isEmpty()) {
            return null;
        }

        Board board = maybeBoard.get();
        Optional<Fork> savedFork = forkRepository.findByBoardAndUser(board, user);
        if (savedFork.isEmpty()) {
            return null;
        }
        forkRepository.delete(savedFork.get());
        return boardService.requestBoard(boardId);
    }

    @Override
    @Transactional
    public List<BoardResForm> getBoard(HttpHeaders headers) {
        User user = userService.findByUserToken(Objects.requireNonNull(headers.get("authorization")).get(0));
        if (user == null) {
            return null;
        }

        List<Board> boardList = forkRepository.findAllByUser(user);
        List<BoardResForm> boardResFormList = boardList.stream().map((b) ->
                BoardResForm.builder()
                        .title(b.getTitle())
                        .id(b.getId())
                        .numReplys(b.getNumReply())
                        .writer(new Writer(b.getWriter()))
                        .createdDate(b.getCreatedDate())
                        .location(new LocationResForm(b.getLocation()))
                        .build()
        ).toList();
        return boardResFormList;
    }
}
