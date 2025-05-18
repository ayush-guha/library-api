package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BookLending;
import com.example.library.model.Borrower;
import com.example.library.repository.BookLendingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LendingService {
    private final BookService bookService;
    private final BorrowerService borrowerService;
    private final BookLendingRepository lendingRepository;

    public LendingService(BookService bookService, BorrowerService borrowerService, BookLendingRepository lendingRepository) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.lendingRepository = lendingRepository;
    }

    public BookLending borrowBook(Long borrowerId, Long bookId) {
        Book book = bookService.getBookById(bookId);
        Borrower borrower = borrowerService.getBorrowerById(borrowerId);

        lendingRepository.findByBookAndReturnedAtIsNull(book).ifPresent(lending -> {
            throw new RuntimeException("Book already borrowed");
        });

        BookLending lending = new BookLending();
        lending.setBook(book);
        lending.setBorrower(borrower);
        lending.setBorrowedAt(LocalDateTime.now());
        return lendingRepository.save(lending);
    }

    public BookLending returnBook(Long borrowerId, Long bookId) {
        Book book = bookService.getBookById(bookId);
        BookLending lending = lendingRepository.findByBookAndReturnedAtIsNull(book)
                .orElseThrow(() -> new RuntimeException("Book is not currently borrowed"));

        if (!lending.getBorrower().getId().equals(borrowerId)) {
            throw new RuntimeException("Book not borrowed by this borrower");
        }

        lending.setReturnedAt(LocalDateTime.now());
        return lendingRepository.save(lending);
    }

    public List<BookLending> getAllCurrentlyBorrowed() {
        return lendingRepository.findAll().stream()
                .filter(lending -> lending.getReturnedAt() == null)
                .collect(Collectors.toList());
    }

    public List<BookLending> getAllLendings() {
        return lendingRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        List<Book> borrowedBooks = lendingRepository.findAll().stream()
            .filter(lending -> lending.getReturnedAt() == null)
            .map(BookLending::getBook)
            .collect(Collectors.toList());
        return allBooks.stream()
            .filter(book -> !borrowedBooks.contains(book))
            .collect(Collectors.toList());
    }
}