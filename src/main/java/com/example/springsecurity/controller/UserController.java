package com.example.springsecurity.controller;

import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal UserDetails userDetails, Principal principal, Model model) {
        model.addAttribute("currentUser", userServiceImpl.findByEmail(userDetails.getUsername()));
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("user", roleService.findByName("ROLE_ADMIN"));
        return "user";
    }
}
