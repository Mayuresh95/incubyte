package io.incubyte;

public class StringCalculator {

    int add(String numbers) throws StringCalculatorException {
        int result = 0;

        if(numbers == null || numbers.length() == 0) {
            return 0;
        }

        String[] literals = numbers.split(",");
        if (literals.length > 2) {
            throw new StringCalculatorException("Max argument length 2 is allowed.");
        }

        for (String literal : literals) {
            result = result + Integer.parseInt(literal.trim());
        }

        return result;
    }
}
