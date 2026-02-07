package com.prasanna.OnlineExaminationSystem.repository;

import com.prasanna.OnlineExaminationSystem.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByUserId(Long userId);
    List<Result> findByExamId(Long examId);
}
