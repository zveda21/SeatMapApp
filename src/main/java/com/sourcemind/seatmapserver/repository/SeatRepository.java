package com.sourcemind.seatmapserver.repository;

import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.model.Floor;
import com.sourcemind.seatmapserver.model.SeatAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<SeatAllocation, Long> {

    Optional<SeatAllocation> findSeatAllocationByEmployee(Employee employee);

    Optional<SeatAllocation> findSeatAllocationByNumber(Integer number);

    Optional<SeatAllocation> findSeatAllocationBySeatUUID(UUID uuid);

    List<SeatAllocation> findSeatAllocationsByFloor(Floor floor);


}