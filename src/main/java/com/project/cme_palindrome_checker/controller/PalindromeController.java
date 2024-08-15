package com.project.cme_palindrome_checker.controller;

import com.project.cme_palindrome_checker.model.PalindromeRequest;
import com.project.cme_palindrome_checker.model.PalindromeResponse;
import com.project.cme_palindrome_checker.service.PalindromeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/palindrome")
public class PalindromeController {

    @Autowired
    private PalindromeService palindromeService;

    @PostMapping
    public CompletableFuture<PalindromeResponse> checkPalindrome(@Valid @RequestBody PalindromeRequest request) {
        return palindromeService.checkPalindrome(request.getUserName(), request.getText());
    }
}
