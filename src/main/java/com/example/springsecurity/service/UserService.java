package com.example.springsecurity.service;

import com.example.springsecurity.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addNewUser(User user);

    public User findById(long id);

    public void updateUser(Long id, User user);

    public void deleteUser(Long id);
}
