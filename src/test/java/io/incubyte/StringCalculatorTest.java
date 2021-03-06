package io.incubyte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    void testAddNewline() throws StringCalculatorException {
        assertEquals(11, stringCalculator.add("1\n2,3\n5"));
    }

    @Test
    @DisplayName("Test StringCalculator.add() with custom delimiter")
    void testAddCustomDelimiter() {
        assertAll(
                () -> assertEquals(16, stringCalculator.add("//;\n2,3\n5;3;3")),
                () -> assertEquals(16, stringCalculator.add("2,3\n5,3,3"))
        );
    }

    @Test
    @DisplayName("Test StringCalculator.add() with negative numbers")
    void testAddNegativeNumber() {

        //Using try catch block to compare exception message
        try {
            stringCalculator.add("1\n2,3\n-5");
        } catch (StringCalculatorException e) {
            System.out.println(e.getMsg());
            if (!"Negatives not allowed - [-5]".equals(e.getMsg())) {
                fail();
            }
        }

        try {
            stringCalculator.add("1\n2,3\n-5,-8");
        } catch (StringCalculatorException e) {
            System.out.println(e.getMsg());
            if (!"Negatives not allowed - [-5, -8]".equals(e.getMsg())) {
                fail();
            }

        }
    }

    @Test
    @DisplayName("Test StringCalculator.add() with number > 1000")
    void testAddNumHigher1000() throws StringCalculatorException {
        assertEquals(11, stringCalculator.add("2,3\n1004\n3\n3,2333"));
    }

    @Test
    @DisplayName("Test StringCalculator.add() delimiter of any length")
    void testAddLengthDelimiter() {
        assertAll(
                () -> assertEquals(11, stringCalculator.add("//[++]\n2,3\n1004++3++3,2333")),
                () -> assertEquals(11, stringCalculator.add("//[XYZ]\n2,3\n1004XYZ3XYZ3,2333"))
        );
    }


    @Test
    @DisplayName("Test StringCalculator.add() delimiter of any length")
    void testAddMultipleDelimiters() {
        assertAll(
                () -> assertEquals(25, stringCalculator.add("//[+++][kkkkk][ww]\n2,3\n1004+++3kkkkk3,2333ww5ww4+++5")),
                () -> assertEquals(25, stringCalculator.add("//[.][k][w]\n2,3\n1004.3k3,2333w5w4.5"))
        );
    }
}