package com.prasanna.OnlineExaminationSystem.repository;

import com.prasanna.OnlineExaminationSystem.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByExamId(Long examId);
}
