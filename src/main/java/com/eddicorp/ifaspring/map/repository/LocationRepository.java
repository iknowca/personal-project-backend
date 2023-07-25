package com.eddicorp.ifaspring.map.repository;

import com.eddicorp.ifaspring.map.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
