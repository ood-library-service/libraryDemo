package com.example.demo.config;

import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new SimplePasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // h2-console 쓰기 위한 설정
        http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                .permitAll()
                .and().csrf()
                .disable().headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));


        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        request -> request
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/status", "/images/**", "/css/**", "/script/**", "/fonts/**", "lib/**", "/user/signup", "/auth/signup", "/", "/test")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/user/login")
                        .loginProcessingUrl("/loginProcess")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                .exceptionHandling();//                .requestMatchers(HttpMethod.GET).permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .authenticated();

        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
}
