package com.example.project.service.Impl;

import com.example.project.model.Users;
import com.example.project.repository.UsersRepository;
import com.example.project.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }

}
