package com.project.cme_palindrome_checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalindromeResponse {
    private String userName;
    private String textValue;
    private boolean isPalindrome;
}
