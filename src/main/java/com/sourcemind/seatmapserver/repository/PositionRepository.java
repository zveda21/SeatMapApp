package com.sourcemind.seatmapserver.repository;

import com.sourcemind.seatmapserver.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findPositionByPositionUUID(UUID positionUUID);
}
