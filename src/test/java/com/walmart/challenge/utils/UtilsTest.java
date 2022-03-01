package com.walmart.challenge.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = { "191", "saas" })
    void shouldAssertWhenASearchTermIsPalindromeSuccessfully(String searchInput) {
        assertTrue(Utils.isPalindrome(searchInput));
    }

}