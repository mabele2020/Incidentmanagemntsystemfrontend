package com.example.project.service;

import com.example.project.model.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UsersService {
    Users saveUser(Users user);

    List<User> getAllUsers();


    boolean deleteUserById(Long id);
}
