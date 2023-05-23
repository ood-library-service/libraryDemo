package com.example.demo.service;

import com.example.demo.model.BookEntity;
import com.example.demo.model.BookState;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public BookEntity retrieve(final Long isbn){
        return bookRepository.findById(isbn).get();
    }

    // 대출
    public void loanBook(final String userPk, final Long isbn){
        final Optional<BookEntity> bookOriginal = bookRepository.findById(isbn); // Id 필요
        final Optional<UserEntity> userOriginal = userRepository.findById(userPk);

        final BookEntity book = bookOriginal.get();
        final UserEntity user = userOriginal.get();

        // 검증 요소
        // 이 책이 대출이 가능한 상태인가 ?
        ValidationService.isAvailable(book.getBookState());
        // 이 사람이 대출이 가능한 사람인가 ?
        ValidationService.userLoanAvailable(user.getBorrowedBooksCnt());

        book.setLoanUser(user.getUsername()); // 대출한 사람을 책에다가 적어줌
        book.setBookState(BookState.BORROWED.getValue());

        LocalDate todayLocalDate = LocalDate.now();
        book.setDateOut(todayLocalDate.plusDays(14));

        bookRepository.save(book);
    }

    // 반납
    public void returnBook(final String userPk, final Long isbn){
        final Optional<BookEntity> bookOriginal = bookRepository.findById(isbn);
        final Optional<UserEntity> userOriginal = userRepository.findById(userPk);

        final BookEntity book = bookOriginal.get();
        final UserEntity user = userOriginal.get();

        // 검증 요소
        // 책이 대출이 되어있는 상태인가 ?
        ValidationService.returnAvailable(book.getBookState());
        // 반납하려는 사람이 책을 빌린사람인가 ?
        ValidationService.isPkEqual(user.getUsername(), book.getLoanUser());

        book.setDateOut(LocalDate.ofEpochDay(0));
        book.setLoanUser("admin");
        book.setBookState(BookState.AVAILABLE.getValue());
        bookRepository.save(book);

        retrieve(isbn);
    }

    // 연장
    public void extendDue(final String userPk, final Long isbn){
        final Optional<BookEntity> bookOriginal = bookRepository.findById(isbn);
        final Optional<UserEntity> userOriginal = userRepository.findById(userPk);

        final BookEntity book = bookOriginal.get();
        final UserEntity user = userOriginal.get();

        // 검증 요소
        // 책이 대출중인가 ?
        ValidationService.isLoaned(book.getBookState());
        // 연장하려는 사람이 책을 빌린사람인가 ?
        ValidationService.isPkEqual(user.getUsername(), book.getLoanUser());

        book.setBookState(BookState.EXTEND.getValue());
        book.setDateOut(book.getDateOut().plusDays(14));
        retrieve(isbn);
    }

    // 책 검색하기
    public List<BookEntity> searchBookList(final String keyWord){
        return bookRepository.findByTitleContaining(keyWord);
    }

    public BookEntity bookDetails(final Long isbn){
        return bookRepository.findByIsbn(isbn);
    }
}
