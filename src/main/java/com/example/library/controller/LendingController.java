package com.example.library.controller;

import com.example.library.model.BookLending;
import com.example.library.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lendings")
public class LendingController {
    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<BookLending> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        return ResponseEntity.ok(lendingService.borrowBook(borrowerId, bookId));
    }

    @PostMapping("/return/{bookId}/{borrowerId}")
    public ResponseEntity<BookLending> returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(lendingService.returnBook(borrowerId, bookId));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<List<BookLending>> getAllBorrowedBooks() {
        return ResponseEntity.ok(lendingService.getAllCurrentlyBorrowed());
    }

    @GetMapping
    public ResponseEntity<List<BookLending>> getAllLendings() {
        return ResponseEntity.ok(lendingService.getAllLendings());
    }

    @GetMapping("/available-books")
    public ResponseEntity<List<?>> getAvailableBooks() {
        return ResponseEntity.ok(lendingService.getAvailableBooks());
    }
}