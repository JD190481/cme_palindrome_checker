package com.project.cme_palindrome_checker.exception;

// Custom exception for handling palindrome errors
public class PalindromeException extends RuntimeException {
    public PalindromeException(String message) {
        super(message);
    }
}
