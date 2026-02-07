package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.Question;
import com.prasanna.OnlineExaminationSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long id, Question questionDetails) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        q.setContent(questionDetails.getContent());
        q.setOptionA(questionDetails.getOptionA());
        q.setOptionB(questionDetails.getOptionB());
        q.setOptionC(questionDetails.getOptionC());
        q.setOptionD(questionDetails.getOptionD());
        q.setAnswer(questionDetails.getAnswer());

        return questionRepository.save(q);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.delete(questionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Question not found")));
    }
}
