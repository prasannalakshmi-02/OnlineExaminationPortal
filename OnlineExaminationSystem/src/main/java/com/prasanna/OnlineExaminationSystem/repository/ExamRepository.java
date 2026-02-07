package com.prasanna.OnlineExaminationSystem.repository;

import com.prasanna.OnlineExaminationSystem.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
