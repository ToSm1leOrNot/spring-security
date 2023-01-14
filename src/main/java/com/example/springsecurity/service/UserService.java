package com.example.springsecurity.service;

import com.example.springsecurity.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void addUser(User user);

    User findUserById(Long id);

    void deleteUserById(Long id);

    User findUserByName(String name);

}
