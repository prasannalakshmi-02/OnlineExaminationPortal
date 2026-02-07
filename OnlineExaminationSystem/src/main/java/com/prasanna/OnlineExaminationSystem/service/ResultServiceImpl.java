package com.prasanna.OnlineExaminationSystem.service;


import com.prasanna.OnlineExaminationSystem.Entity.Exam;
import com.prasanna.OnlineExaminationSystem.Entity.Question;
import com.prasanna.OnlineExaminationSystem.Entity.Result;
import com.prasanna.OnlineExaminationSystem.Entity.User;
import com.prasanna.OnlineExaminationSystem.repository.ExamRepository;
import com.prasanna.OnlineExaminationSystem.repository.QuestionRepository;
import com.prasanna.OnlineExaminationSystem.repository.ResultRepository;
import com.prasanna.OnlineExaminationSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamRepository examRepository;

    @Override
    public Result submitExam(Long userId, Long examId, Map<Long, String> userAnswers) {
        // 1. Fetch User and Exam
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        List<Question> questions = questionRepository.findByExamId(examId);

        int score = 0;
        for (Question q : questions) {
            String selectedAnswer = userAnswers.get(q.getId());

            // specific check: make sure answer isn't null and matches correct answer
            if (selectedAnswer != null && selectedAnswer.equals(q.getAnswer())) {
                score++;
            }
        }
        Result result = new Result();
        result.setUser(user);
        result.setExam(exam);
        result.setScore(score);
        result.setTotalQuestions(questions.size());

        return resultRepository.save(result);
    }

    public Result getResultById(Long id){
        return resultRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Result not found"));
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
