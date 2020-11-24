package com.webQuiz.demo.controllers;

import com.webQuiz.demo.models.Quiz;
import com.webQuiz.demo.models.User;
import com.webQuiz.demo.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class QuizController {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/quizzes")
    public String getQuizzes(Model model) {
        model.addAttribute("quizzes", quizRepository.findAll());
        return "quizzes";
    }

    @GetMapping("/solve")
    public String selectQuiz(Model model) {
        model.addAttribute("quizzes", quizRepository.findAll());
        return "selectQuiz";
    }

    @PostMapping("/solve")
    public String selectQuiz(HttpServletRequest httpServletRequest) {
        return "redirect:/solve/" + httpServletRequest.getParameter("id");
    }

    @GetMapping("/solve/{id}")
    public String solveQuiz(@PathVariable("id") long id, Model model) {
        model.addAttribute("quiz", quizRepository.findById(id).get());
        return "solveQuiz";
    }

    @PostMapping("/solve/{id}")
    public String solveQuiz(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        String answer = httpServletRequest.getParameter("answer");
        if (answer.equals(quizRepository.findById(id).get().getAnswer())) {
            return "redirect:/solve/{id}/success";
        } else {
            return "redirect:/solve/{id}/fail";
        }
    }

    @GetMapping("/solve/{id}/success")
    public String Success() {
        return "Success.html";
    }

    @GetMapping("/solve/{id}/fail")
    public String Fail(@PathVariable("id") long id, Model model) {
        model.addAttribute("quiz", quizRepository.findById(id).get());
        return "Fail.html";
    }

    @GetMapping("/loggedIn")
    public String logged() {
        return "loggedIn";
    }

    @GetMapping("/quiz/form")
    public String quizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "quizForm";
    }

    @PostMapping("/quiz/form")
    public String addQuiz(@Valid Quiz quiz, Errors errors, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "quizForm";
        }
        quiz.setAuthor(user);
        quizRepository.save(quiz);
        return "redirect:/loggedIn";
    }

    @GetMapping("/quiz/delete")
    public String quizDeleteForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("quizzes", quizRepository.findByAuthor(user));
        return "deleteQuiz";
    }

    @PostMapping("/quiz/delete")
    public String deleteQuiz(HttpServletRequest httpServletRequest) {
        long id = Integer.valueOf(httpServletRequest.getParameter("id"));
        quizRepository.deleteById(id);
        return "redirect:/loggedIn";
    }

    @GetMapping("/user/quizzes")
    public String userQuizzes(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("quizzes", quizRepository.findByAuthor(user));
        return "userQuizzes";
    }

}
