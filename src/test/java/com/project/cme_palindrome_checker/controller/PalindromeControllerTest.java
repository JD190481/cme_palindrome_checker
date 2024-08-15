package com.project.cme_palindrome_checker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cme_palindrome_checker.model.PalindromeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PalindromeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    PalindromeRequest valid_palindrome;
    PalindromeRequest invalid_palindrome;

    @BeforeEach
    void setUp() {
        valid_palindrome = new PalindromeRequest("madam", "JD");
        invalid_palindrome = new PalindromeRequest("asd 345", "testUser");
    }

    @Test
    public void testValidPalindrome() throws Exception {
        String validPalindrome = objectMapper.writeValueAsString(valid_palindrome);
        MvcResult mvcResult = mockMvc.perform(post("/api/palindrome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPalindrome))
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(asyncDispatch(mvcResult)).andDo(print()).andExpect(jsonPath("$.palindrome").value("true"));
    }

    @Test
    public void testInValidPalindrome() throws Exception {
        String inValidPalindrome = objectMapper.writeValueAsString(invalid_palindrome);
        MvcResult mvcResult = mockMvc.perform(post("/api/palindrome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inValidPalindrome))
                        .andExpect(status().isBadRequest())
                        .andReturn();
    }
}