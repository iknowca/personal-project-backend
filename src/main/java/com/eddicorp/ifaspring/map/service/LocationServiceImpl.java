package com.eddicorp.ifaspring.map.service;

import com.eddicorp.ifaspring.board.entity.Board;
import com.eddicorp.ifaspring.board.repository.BoardRepository;
import com.eddicorp.ifaspring.map.entity.Location;
import com.eddicorp.ifaspring.map.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    final LocationRepository locationRepository;
    final BoardRepository boardRepository;
    @Override
    public List<Board> getBoardByLocation(Map<String, String> locationMap) {
        log.info("aaaaaa");
        Location location = Location.builder()
                .d_o(locationMap.get("d_o"))
                .si(locationMap.get("si"))
                .gu(locationMap.get("gu"))
                .dong(locationMap.get("dong"))
                .lat(Double.valueOf(locationMap.get("lat")))
                .lng(Double.valueOf(locationMap.get("lng")))
                .build();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        switch (locationMap.get("level")) {
            case "1"-> {
                List<Board> boardl = boardRepository.findByLocationLevel1(locationMap, pageable);
                log.info(boardl.toString());
                return boardl;
            }
            case "2"-> {
                log.info("2");

                return boardRepository.findByLocationLevel2(locationMap, pageable);
            }
            case "3"-> {
                log.info("3");
                return boardRepository.findByLocationLevel3(locationMap, pageable);
            }
            case "4"-> {
                log.info("4");
                return boardRepository.findByLocationLevel4(locationMap, pageable);
            }
            default-> {
                log.info("5");
                return boardRepository.findByLocationLevel5(locationMap, pageable);
            }
        }
    }
}
