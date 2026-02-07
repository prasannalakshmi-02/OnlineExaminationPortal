package com.prasanna.OnlineExaminationSystem.controller;


import com.prasanna.OnlineExaminationSystem.Entity.Exam;
import com.prasanna.OnlineExaminationSystem.Entity.Question;
import com.prasanna.OnlineExaminationSystem.Entity.Result;
import com.prasanna.OnlineExaminationSystem.Entity.User;
import com.prasanna.OnlineExaminationSystem.service.ExamService;
import com.prasanna.OnlineExaminationSystem.service.QuestionService;
import com.prasanna.OnlineExaminationSystem.service.ResultService;
import com.prasanna.OnlineExaminationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String studentDashboard(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "student/dashboard";
    }

    @GetMapping("/exam/{id}")
    public String takeExam(@PathVariable Long id, Model model) {
        Exam exam = examService.getExamById(id);
        List<Question> questions = questionService.getQuestionsByExamId(id);

        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);

        return "student/exam-page";
    }

    @PostMapping("/exam/submit")
    public String submitExam(@RequestParam Map<String, String> allParams,
                             @RequestParam("examId") Long examId,
                             Principal principal) {

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Map<Long, String> userAnswers = new HashMap<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("question_")) {
                Long questionId = Long.parseLong(entry.getKey().replace("question_", ""));
                userAnswers.put(questionId, entry.getValue());
            }
        }
        Result result = resultService.submitExam(user.getId(), examId, userAnswers);

        return "redirect:/student/result/" + result.getId();
    }

    @GetMapping("/result/{id}")
    public String viewResult(@PathVariable Long id, Model model) {
        Result result = resultService.getResultById(id);
        model.addAttribute("result", result);
        return "student/result";
    }
}
