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

    // 1. Admin Dashboard (Lists all exams)
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Fetch all exams to display in a table
        List<Exam> exams = examService.getAllExams();

        // Add data to the model so Thymeleaf can access it as ${exams}
        model.addAttribute("exams", exams);

        return "admin/dashboard"; // This looks for admin/dashboard.html
    }

    @GetMapping("/add-exam")
    public String showAddExamForm(Model model) {
        model.addAttribute("exam", new Exam()); // Bind empty object to form
        return "admin/add-exam";
    }

    // Process the form submission
    @PostMapping("/add-exam")
    public String addExam(@ModelAttribute Exam exam) {
        examService.addExam(exam);
        return "redirect:/admin/dashboard"; // Go back to dashboard after saving
    }

    // View all questions for a specific exam
    @GetMapping("/exam/{id}/questions")
    public String viewQuestions(@PathVariable Long id, Model model) {
        Exam exam = examService.getExamById(id);
        List<Question> questions = questionService.getQuestionsByExamId(id);

        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);

        return "admin/view-questions";
    }

    // Show "Add Question" form (Pass the exam ID to the form!)
    @GetMapping("/exam/{id}/add-question")
    public String showAddQuestionForm(@PathVariable Long id, Model model) {
        Question question = new Question();
        // We need to link this question to the exam
        question.setExam(examService.getExamById(id));

        model.addAttribute("question", question);
        model.addAttribute("examId", id); // Pass ID to keep track

        return "admin/add-question";
    }

    // Process the "Add Question" form
    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question, @RequestParam("examId") Long examId) {
        // 1. Fetch the exam object using the ID from the form
        Exam exam = examService.getExamById(examId);

        // 2. Link the exam to the question
        question.setExam(exam);

        // 3. Save
        questionService.addQuestion(question);

        return "redirect:/admin/exam/" + examId + "/questions"; // Redirect back to question list
    }

    // View All Student Results
    @GetMapping("/results")
    public String viewAllResults(Model model) {
        List<Result> results = resultService.getAllResults();
        model.addAttribute("results", results);
        return "admin/results";
    }
}
