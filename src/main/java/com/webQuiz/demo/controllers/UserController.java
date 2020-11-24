package com.webQuiz.demo.controllers;

import com.webQuiz.demo.models.User;
import com.webQuiz.demo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/register")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "userRegistration";
    }

    @PostMapping
    public String Registration(@Valid User user, Errors errors, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameTaken", new Error("Username has to be unique"));
            model.addAttribute("takenUsername", user.getUsername());
            return "userRegistration";
        }
        if (errors.hasErrors()) {
            return "userRegistration";
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}
