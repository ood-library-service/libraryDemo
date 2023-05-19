package com.example.demo.repository;

import com.example.demo.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContaining(String keyWord);

    BookEntity findByIsbn(Long isbn);

}
