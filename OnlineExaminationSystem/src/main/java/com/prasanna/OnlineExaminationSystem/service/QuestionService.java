package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.Question;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);
    Question updateQuestion(Long id, Question question);
    List<Question> getQuestionsByExamId(Long examId);
    Question getQuestionById(Long id);
    void deleteQuestion(Long id);
}
