package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.Result;

import java.util.List;
import java.util.Map;

public interface ResultService {

    Result submitExam(Long userId, Long examId, Map<Long, String> userAnswers);
    Result getResultById(Long id);
    List<Result> getAllResults();
}
