package com.project.cme_palindrome_checker.service;

import com.project.cme_palindrome_checker.component.PalindromeCache;
import com.project.cme_palindrome_checker.component.PalindromePersistence;
import com.project.cme_palindrome_checker.exception.PalindromeException;
import com.project.cme_palindrome_checker.model.PalindromeResponse;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class PalindromeService {

    private final PalindromeCache cache;
    private final PalindromePersistence persistence;

    public PalindromeService(PalindromeCache cache, PalindromePersistence persistence) {
        this.cache = cache;
        this.persistence = persistence;
        persistence.loadCache();
    }

    public CompletableFuture<PalindromeResponse> checkPalindrome(String username, String text) {
        // Validate input
        if (text == null || text.trim().isEmpty() || !text.matches("[a-zA-Z]+")) {
            throw new PalindromeException("Input must be a non-empty string containing only letters.");
        }

        // Check cache first
        if (cache.exists(text)) {
            return CompletableFuture.completedFuture(new PalindromeResponse(username, text, cache.isPalindrome(text)));
        }

        // Check if palindrome
        boolean isPalindrome = isPalindrome(text);

        // Store result in cache and file
        cache.put(text, isPalindrome);
        CompletableFuture.runAsync(() -> persistence.saveResult(username, text, isPalindrome));

        return CompletableFuture.completedFuture(new PalindromeResponse(username, text, isPalindrome));
    }

    private boolean isPalindrome(String text) {
        String cleaned = text.toLowerCase();
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }
}
