package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    final private UserRepository userRepository;

    @GetMapping("/")
    public String index(HttpSession session){
        session.setAttribute("test", "test");
        return "/index";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "/user/login";
    }

    @GetMapping("/user/signup")
    public String signupForm(){
        return "/user/signup";
    }

    @PostMapping("/auth/signup")
    public String signUp(UserDTO userDTO){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity user = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);

        List<UserEntity> userEntityList = userRepository.findAll();


        for (UserEntity userEntity : userEntityList) {
            log.warn(String.valueOf(userEntity));
        }

        return "redirect:/";
    }

    @GetMapping("/user/myPage")
    public String myPageForm(Principal principal, HttpSession session){
        session.setAttribute("username", principal.getName());
        return "/user/myPage";
    }

}
