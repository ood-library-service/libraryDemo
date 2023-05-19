package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userrepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userrepository;
    }

    public Optional<UserEntity> findOne(String username){
        return userRepository.findByUsername(username);
    }

}
