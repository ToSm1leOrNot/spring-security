package com.example.springsecurity.controller;


import com.example.springsecurity.models.User;
import com.example.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser(Model model, Principal principal) {
        User messages = userService.findUserByName(principal.getName());
        model.addAttribute("messages", messages);
        return "user";
    }
}
