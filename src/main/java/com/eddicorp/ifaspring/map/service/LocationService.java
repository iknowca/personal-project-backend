package com.eddicorp.ifaspring.map.service;

import com.eddicorp.ifaspring.board.controller.form.BoardResForm;
import com.eddicorp.ifaspring.board.entity.Board;

import java.util.List;
import java.util.Map;

public interface LocationService {
    List<Board> getBoardByLocation(Map<String, String> locationMap);
}
