package com.example.library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BookLending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Borrower borrower;

    @OneToOne
    private Book book;

    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Borrower getBorrower() {
        return borrower;
    }
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }
    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }
    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }
    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }
}