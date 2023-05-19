package com.example.demo.controller;

import com.example.demo.model.BookEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    @GetMapping("/bookList")
    public String bookListForm(HttpSession session, @RequestParam("keyWord") String keyWord){
        List<BookEntity> bookList = libraryService.searchBookList(keyWord);
        session.setAttribute("bookList", bookList);
        return "/library/bookList";
    }

    @GetMapping("/bookDetail")
    public String bookDetailForm(HttpSession session, @RequestParam("isbn") Long isbn){
        BookEntity book = libraryService.bookDetails(isbn);
        session.setAttribute("bookDetail", book);
        return "/library/bookDetail";
    }

}
