package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book registerBook(Book book) {
        repository.findByIsbn(book.getIsbn()).ifPresent(existing -> {
            if (!existing.getTitle().equals(book.getTitle()) || !existing.getAuthor().equals(book.getAuthor())) {
                throw new RuntimeException("A book with this ISBN already exists with a different title or author.");
            }
        });
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}