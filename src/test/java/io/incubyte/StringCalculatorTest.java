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
        assertEquals(0, stringCalculator.add(""), "Empty inputs should return 0 always");
    }
}