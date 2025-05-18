package com.example.library.controller;

import com.example.library.LibraryApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LibraryApiApplication.class)
@ActiveProfiles("test")
public class LendingControllerTest {
    @Test
    void contextLoads() {
        // Basic context load test
    }
}
