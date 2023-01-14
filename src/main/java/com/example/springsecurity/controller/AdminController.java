package com.example.springsecurity.controller;

import com.example.springsecurity.models.User;
import com.example.springsecurity.repositories.RoleRepository;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public class AdminsController {
        private final UserService userService;
        private final UserServiceImpl userServiceImpl;
        private final RoleRepository roleRepository;
        private final RoleService roleService;

        @Autowired
        public AdminsController(UserService userService, UserServiceImpl userServiceImpl, RoleRepository roleRepository, RoleService roleService) {
            this.userService = userService;
            this.userServiceImpl = userServiceImpl;
            this.roleRepository = roleRepository;
            this.roleService = roleService;
        }

        @GetMapping
        public String showUsersList(@AuthenticationPrincipal UserDetails userDetails, Principal principal, Model model) {
            model.addAttribute("usersList", userService.getAllUsers());
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("userAuthorized", userServiceImpl.findByEmail(userDetails.getUsername()));
            model.addAttribute("roles", roleService.findAll());
            return "adminPage";
        }

        @GetMapping("/new")
        public String showNewUserForm(@AuthenticationPrincipal UserDetails userDetails, Principal principal, Model model) {
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("userAuthorized", userServiceImpl.findByEmail(userDetails.getUsername()));
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("user", new User());
            return "/new";
        }

        @PostMapping("/new")
        public String saveNewUser(@ModelAttribute("user") User user, BindingResult result) {
            if (result.hasErrors()) {
                return "redirect:/new";
            }
            userService.addNewUser(user);
            return "redirect:/admin";
        }

        @PatchMapping("/edit/{id}")
        public String updateUser(@ModelAttribute("user") User user, BindingResult result, @PathVariable("id") Long id) {

            if (result.hasErrors()) {
                return "redirect:/edit/{id}";
            }

            userService.updateUser(id, user);
            return "redirect:/admin";
        }

        @DeleteMapping("/delete/{id}")
        public String deleteUser(@PathVariable("id") Long id) {
            userService.deleteUser(id);
            return "redirect:/admin";
        }
    }
}
