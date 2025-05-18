package com.example.library.service;

import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    private final BorrowerRepository repository;

    public BorrowerService(BorrowerRepository repository) {
        this.repository = repository;
    }

    public Borrower registerBorrower(Borrower borrower) {
        repository.findByEmail(borrower.getEmail()).ifPresent(existing -> {
            throw new RuntimeException("A borrower with this email already exists.");
        });
        return repository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return repository.findAll();
    }

    public Borrower getBorrowerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Borrower not found"));
    }
}