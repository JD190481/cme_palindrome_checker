package com.project.cme_palindrome_checker.component;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PalindromeCache {

    private final Map<String, Boolean> cache = new HashMap<>();

    public boolean exists(String text) {
        return cache.containsKey(text);
    }

    public boolean isPalindrome(String text) {
        return cache.get(text);
    }

    public void put(String text, boolean isPalindrome) {
        cache.put(text, isPalindrome);
    }
}
