package com.srms.backend.repository;

import com.srms.backend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByStudentId(Long studentId);
}