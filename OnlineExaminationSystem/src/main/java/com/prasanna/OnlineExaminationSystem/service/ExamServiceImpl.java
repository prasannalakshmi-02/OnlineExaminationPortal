package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.Exam;
import com.prasanna.OnlineExaminationSystem.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;


    @Override
    public Exam addExam(Exam exam) {
        if (exam.getTimeLimit() == null || exam.getTimeLimit() <= 0) {
            exam.setTimeLimit(30);
        }
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Exam is not found with id" + id));
        return exam;
    }


    @Override
    public Exam updateExam(Long id, Exam exam) {
        Exam ex = examRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Exam not found"));
        ex.setTitle(exam.getTitle());
        ex.setTimeLimit(exam.getTimeLimit());
        ex.setQuestions(exam.getQuestions());
        return examRepository.save(ex);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.delete(examRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Exam not found")));
    }
}
