package com.sourcemind.seatmapserver.repository;

import com.sourcemind.seatmapserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmployee_Email(String employeeEmail);
}
