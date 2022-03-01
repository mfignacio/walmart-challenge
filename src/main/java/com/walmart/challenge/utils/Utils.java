package com.walmart.challenge.utils;

import java.util.stream.IntStream;

public class Utils {

    public static boolean isPalindrome(String text) {
        if (text.length() >= Constants.MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH) {
            String temp = text.replaceAll("\\s+", "").toLowerCase();
            return IntStream.range(0, temp.length() / 2)
                    .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
        } else {
            return false;
        }
    }
}
