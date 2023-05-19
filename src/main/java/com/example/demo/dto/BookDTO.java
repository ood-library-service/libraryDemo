package com.example.demo.dto;

import com.example.demo.model.BookEntity;

import java.time.LocalDate;

public class BookDTO {

    public static class Detail{
        private Long isbn;
        private String title;
        private String author;
        private LocalDate dateOut;
        private String loanUser;
        private String bookState;

        public Detail(final BookEntity entity){
            this.isbn = entity.getIsbn();
            this.title = entity.getTitle();
            this.author = entity.getAuthor();
            this.dateOut = entity.getDateOut();
            this.loanUser = entity.getLoanUser();
            this.bookState = entity.getBookState();
        }
    }
}
