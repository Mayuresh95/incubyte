package io.incubyte;

public class StringCalculator {

    int add(String numbers) throws StringCalculatorException {
        int result = 0;

        if(numbers == null || numbers.length() == 0) {
            return 0;
        }

        String[] literals = numbers.split("[\\n,;]+");

        for (String literal : literals) {
            result = result + Integer.parseInt(literal.trim());
        }

        return result;
    }
}
