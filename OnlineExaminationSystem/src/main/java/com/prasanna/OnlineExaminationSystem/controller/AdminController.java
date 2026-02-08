package com.prasanna.OnlineExaminationSystem.controller;


import com.prasanna.OnlineExaminationSystem.Entity.Exam;
import com.prasanna.OnlineExaminationSystem.Entity.Question;
import com.prasanna.OnlineExaminationSystem.Entity.Result;
import com.prasanna.OnlineExaminationSystem.service.ExamService;
import com.prasanna.OnlineExaminationSystem.service.QuestionService;
import com.prasanna.OnlineExaminationSystem.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
     
        List<Exam> exams = examService.getAllExams();

      
        model.addAttribute("exams", exams);

        return "admin/dashboard"; 
    }

    @GetMapping("/add-exam")
    public String showAddExamForm(Model model) {
        model.addAttribute("exam", new Exam()); 
        return "admin/add-exam";
    }

    @PostMapping("/add-exam")
    public String addExam(@ModelAttribute Exam exam) {
        examService.addExam(exam);
        return "redirect:/admin/dashboard"; 
    }


    @GetMapping("/exam/{id}/questions")
    public String viewQuestions(@PathVariable Long id, Model model) {
        Exam exam = examService.getExamById(id);
        List<Question> questions = questionService.getQuestionsByExamId(id);

        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);

        return "admin/view-questions";
    }

    
    @GetMapping("/exam/{id}/add-question")
    public String showAddQuestionForm(@PathVariable Long id, Model model) {
        Question question = new Question();
        
        question.setExam(examService.getExamById(id));

        model.addAttribute("question", question);
        model.addAttribute("examId", id); 

        return "admin/add-question";
    }

    
    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question, @RequestParam("examId") Long examId) {
       
        Exam exam = examService.getExamById(examId);

        question.setExam(exam);

        questionService.addQuestion(question);

        return "redirect:/admin/exam/" + examId + "/questions"; 
    }

    @GetMapping("/results")
    public String viewAllResults(Model model) {
        List<Result> results = resultService.getAllResults();
        model.addAttribute("results", results);
        return "admin/results";
    }



    @GetMapping("/question/edit/{id}")
    public String showEditQuestionForm(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("examId", question.getExam().getId());
        return "admin/edit-question";
    }

    @PostMapping("/question/update/{id}")
    public String updateQuestion(@PathVariable Long id, @ModelAttribute Question question, @RequestParam("examId") Long examId) {
        questionService.updateQuestion(id, question);
        return "redirect:/admin/exam/" + examId + "/questions";
    }
}

