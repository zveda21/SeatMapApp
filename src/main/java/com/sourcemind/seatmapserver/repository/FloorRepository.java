package com.sourcemind.seatmapserver.repository;

import com.sourcemind.seatmapserver.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    Optional<Floor> findFloorByFloorUUID(UUID floorUUID);

    Optional<Floor> findFloorByNumber(Integer number);
}