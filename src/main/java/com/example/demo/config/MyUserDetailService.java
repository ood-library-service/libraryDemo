package com.example.demo.config;

import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> findOne = userService.findOne(username);
        UserEntity user = findOne.orElseThrow( () -> new UsernameNotFoundException("없는 사용자입니"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

    }
}
