package com.project.cme_palindrome_checker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalindromeRequest {
    @NotBlank(message = "Text must not be blank")
    @Size(min = 1, max = 100, message = "Text must be between 1 and 100 characters")
    private String text;
    private String userName;
}
