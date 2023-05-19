package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationService {

    public static void isAvailable(final String state){ // 대출 가능한 상태인가
        if (Objects.equals(state, "available")){
            log.warn("대출 가능 상태입니다.");
        }
        else if (Objects.equals(state, "loaned")){
            log.warn("이미 대출 중 입니다.");
            throw new RuntimeException("이미 대출 중 입니다.");
        }
        else if (Objects.equals(state, "extended")){
            log.warn("이미 대출 중 입니다.");
            throw new RuntimeException("이미 대출 중 입니다.");
        }

        log.warn("unexpected state");
        throw new RuntimeException("unexpected state");
    }

    public static void isLoaned(final String state){
        if (Objects.equals(state, "loaned")){
            log.warn("연장 가능 상태입니다.");
        }else if(Objects.equals(state, "available")){
            log.warn("대출 가능 상태입니다.");
            throw new RuntimeException("대출 가능 상태입니다.");
        }else if(Objects.equals(state, "extended")){
            log.warn("연장은 1회만 가능합니다.");
            throw new RuntimeException("연장은 1회만 가능합니다.");
        }

        log.warn("unexpected state");
        throw new RuntimeException("unexpected state");

    }

    public static void returnAvailable(final String state){
        if(Objects.equals(state, "loaned") || Objects.equals(state,"extended")){
            log.warn("반납 가능 상태입니다.");
        }else if(Objects.equals(state, "available")){
            log.warn("대출 가능 상태입니다.");
            throw new RuntimeException("대출가능 상태입니다.");
        }

        log.warn("unexpected state");
        throw new RuntimeException("unexpected state");

    }

    public static void userLoanAvailable(final Integer borrowedBooksCnt){
        if(borrowedBooksCnt > 6){
            log.warn("대출 책 수 초괴");
            throw new RuntimeException("대출 책 수 초괴");
        }

        log.warn("대출 가능");

    }

    public static void isPkEqual(final String userPk, final String loanUser){
        if(!Objects.equals(userPk, loanUser)){
            log.warn("불잂치 합니다.");
            throw new RuntimeException("불잂치 합니다.");
        }
        log.warn("잏치 합니다.");
    }

}
