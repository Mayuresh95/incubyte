package io.incubyte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    StringCalculator stringCalculator;

    @BeforeEach
    void initEach() {
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("Test StringCalculator.add() with empty input")
    void testAddEmpty() {
        assertAll(
                () -> assertEquals(0, stringCalculator.add(""), "Empty inputs should return 0 always"),
                () -> assertEquals(0, stringCalculator.add(null), "Empty inputs should return 0 always"));
    }


    @Test
    @DisplayName("Test StringCalculator.add() with no limit to argument length")
    void testAddArgumentLength() {
        assertAll(
                () -> assertEquals(11, stringCalculator.add("1,2,3,5")),
                () -> assertEquals(1, stringCalculator.add("1")),
                () -> assertEquals(5, stringCalculator.add("2, 3"))
        );
    }

    @Test
    @DisplayName("Test StringCalculator.add() with '\n' as a delimiter")
    void testAddNewline() {
        assertAll(
                () -> assertEquals(11, stringCalculator.add("1\n2,3\n5"))
        );
    }
}