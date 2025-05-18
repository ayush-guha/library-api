package com.example.library.controller;

import com.example.library.model.Borrower;
import com.example.library.service.BorrowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.registerBorrower(borrower));
    }

    @GetMapping
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        return ResponseEntity.ok(borrowerService.getAllBorrowers());
    }
}