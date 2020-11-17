package com.webQuiz.demo.controllers;

import com.webQuiz.demo.models.User;
/*import com.webQuiz.demo.repositories.UserRepository;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

/*
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/

    @GetMapping("/user/register")
    public String userRegister(Model model){
        model.addAttribute("user", new User());
        return "userRegistration";
    }
}
