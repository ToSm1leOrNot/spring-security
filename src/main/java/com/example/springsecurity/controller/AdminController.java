package com.example.springsecurity.controller;

import com.example.springsecurity.models.Role;
import com.example.springsecurity.models.User;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Component
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String showAllUser(ModelMap model) {
        List<User> messages = userService.getAllUser();
        model.addAttribute("messages", messages);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(ModelMap model) {

        model.addAttribute("messages", new User());

        model.addAttribute("roles", roleService.getAllRole());


        return "userInfo";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("messages") User username) {

        userService.addUser(username);

        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";

    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        User messages = userService.findUserById(id);
        model.addAttribute("messages", messages);
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "userInfo";
    }

}
