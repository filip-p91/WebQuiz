package com.webQuiz.demo.controllers;

import com.webQuiz.demo.models.Quiz;
/*import com.webQuiz.demo.repositories.QuizRepository;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class QuizController {

/*
    private final QuizRepository quizRepository;

    @Autowired
    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
*/

    @GetMapping("/quizzes")
    public String showQuizzes(Model model){
        /*Iterable<Quiz> quizzes = quizRepository.findAll();*/
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz(1, "Is it?", new String[]{"a", "b"}, 1));
        model.addAttribute("quizzes", quizzes);
        return "quizzes";
    }

    @GetMapping("/form")
    public String quizForm(Model model){
        model.addAttribute("quiz", new Quiz());
        return "quizForm";
    }

}
