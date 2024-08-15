package com.project.cme_palindrome_checker.component;

import com.project.cme_palindrome_checker.exception.PalindromeException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class PalindromePersistence {

    private static final String FILE_PATH = "src/main/resources/static/palindrome_storage.txt";
    private final PalindromeCache cache;

    public PalindromePersistence(PalindromeCache cache) {
        this.cache = cache;
    }

    @PostConstruct
    public void loadCache() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String text = parts[1].split(" is ")[0];
                boolean isPalindrome = parts[1].contains("a palindrome");
                cache.put(text, isPalindrome);
            }
        } catch (IOException e) {
            throw new PalindromeException(e.getLocalizedMessage());
        }
    }

    public void saveResult(String username, String text, boolean isPalindrome) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.format("%s: %s is %s%n", username, text, isPalindrome ? "a palindrome" : "not a palindrome"));
        } catch (IOException e) {
            throw new PalindromeException(text);
        }
    }
}

