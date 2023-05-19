package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    @Column(name = "isbn")
    private Long isbn;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "author")
    private String author;

    @Column(nullable = false, name = "dateout")
    @Builder.Default
    private LocalDate dateOut = LocalDate.ofEpochDay(0000-00-00); // 반납일

    @Column(name = "loanuser")
    @Builder.Default
    private String loanUser = "admin";

    @Column(name = "bookstate")
    @Builder.Default
    private String bookState = BookState.AVAILABLE.getValue();

}
