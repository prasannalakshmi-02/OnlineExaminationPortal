package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.Exam;

import java.util.List;

public interface ExamService {

    List<Exam> getAllExams();
    Exam getExamById(Long id);
    Exam addExam(Exam exam);
    Exam updateExam(Long id, Exam exam);
    void deleteExam(Long id);
}
