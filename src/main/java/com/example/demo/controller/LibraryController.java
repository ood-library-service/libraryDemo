package com.example.demo.controller;

import com.example.demo.model.BookEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LibraryService libraryService;

    @GetMapping("/bookList")
    public String bookListForm(HttpSession session, @RequestParam("keyWord") String keyWord){
        List<BookEntity> bookList = libraryService.searchBookList(keyWord);
        session.setAttribute("bookList", bookList);
        return "/library/bookList";
    }

    @GetMapping("/bookDetail")
    public String bookDetailForm(HttpSession session, @RequestParam("isbn") Long isbn, Principal principal){
        BookEntity book = libraryService.bookDetails(isbn);
        session.setAttribute("username", principal.getName());
        session.setAttribute("bookDetail", book);
        return "/library/bookDetail";
    }

    @PostMapping("/loanBook")
    public String loanBook(Principal principal, @RequestParam("isbn") Long isbn, HttpSession session){
        String userPk = userRepository.findByUsername(principal.getName()).get().getPk();
        libraryService.loanBook(userPk, isbn);

        BookEntity book = libraryService.bookDetails(isbn);
        session.setAttribute("bookDetail", book);
        return "/library/bookDetail";
    }

    @PostMapping("/returnBook") // 반납하기
    public String returnBook(Principal principal, @RequestParam("isbn") Long isbn, HttpSession session){
        String userPk = userRepository.findByUsername(principal.getName()).get().getPk();
        libraryService.returnBook(userPk, isbn);

        BookEntity book = libraryService.bookDetails(isbn);
        session.setAttribute("bookDetail", book);
        return "/library/bookDetail";
    }

    @PostMapping("/extendBook") // 연장하기
    public String extendBook(Principal principal, @RequestParam("isbn") Long isbn, HttpSession session){
        String userPk = userRepository.findByUsername(principal.getName()).get().getPk();
        libraryService.extendDue(userPk, isbn);

        BookEntity book = libraryService.bookDetails(isbn);
        session.setAttribute("bookDetail", book);
        return "/library/bookDetail";
    }

}
