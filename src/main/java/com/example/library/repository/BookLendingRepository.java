package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.BookLending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLendingRepository extends JpaRepository<BookLending, Long> {
    Optional<BookLending> findByBookAndReturnedAtIsNull(Book book);
}