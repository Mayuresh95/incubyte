package io.incubyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    final String DECLARE_DELIMITER = "//";

    int add(String numbers) throws StringCalculatorException {
        int result = 0;

        if(numbers == null || numbers.length() == 0) {
            return 0;
        }

        StringBuilder regex = new StringBuilder("(\\n|,");
        String[] literals;

        if (numbers.startsWith(DECLARE_DELIMITER)) {
            String[] tempArray = numbers.split("\\n", 2);

            String customDelimiter = tempArray[0].substring(2).trim();
            if (customDelimiter.length() > 2 && customDelimiter.startsWith("[") && customDelimiter.endsWith("]")) {
                customDelimiter = customDelimiter.substring(1, customDelimiter.length() - 1);
            } else {
                customDelimiter = String.valueOf(tempArray[0].charAt(2));
            }

            regex.append("|");
            if (isSpecialCharacter(customDelimiter)) {
                regex.append("\\");
            }
            regex.append(customDelimiter);
            numbers = tempArray[1];
        }

        regex.append(")+");
        literals = numbers.split(regex.toString());

        List<Integer> negativeNumbers = new ArrayList<>();
        for (String literal : literals) {
            Integer number = Integer.parseInt(literal.trim());
            result = (number > 1000) ? result : result + number;

            if (number < 0) {
                negativeNumbers.add(number);
            }
        }

        if (negativeNumbers.size() > 0) {
            throw new StringCalculatorException("Negatives not allowed - "+ negativeNumbers);
        }

        return result;
    }

    private boolean isSpecialCharacter(String customDelimiter) {
        List<String> list = Arrays.asList(".", "\\", "+", "*", "?", "[", "^", "]", "$", "(", ")", "{", "}", "=", "!", "<", ">", "|", ":", "-");

        for (String specialChar : list) {
            if (customDelimiter.startsWith(specialChar)) {
                return true;
            }
        }
        return false;
    }
}