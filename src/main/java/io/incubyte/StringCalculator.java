package io.incubyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    final String DECLARE_DELIMITER = "//";

    int add(String numbers) throws StringCalculatorException {
        int result = 0;

        if(numbers == null || numbers.length() == 0) {
            return 0;
        }

        StringBuilder regex = new StringBuilder("(\\n|,");
        String[] literals;

        //Check whether user has declared his delimiter
        if (numbers.startsWith(DECLARE_DELIMITER)) {
            String[] tempArray = numbers.split("\\n", 2);

            String customDelimiter = tempArray[0].substring(2).trim();

            //check whether delimiter is of multiple length
            if (customDelimiter.length() > 2 && customDelimiter.startsWith("[") && customDelimiter.endsWith("]")) {

                Matcher matcher = Pattern.compile("\\[.*?\\]").matcher(customDelimiter);
                while (matcher.find()) {
                    String delimiter = matcher.group();
                    customDelimiter = delimiter.substring(1, delimiter.length() - 1);
                    addCustomDelimiter(customDelimiter, regex);
                }
            } else {
                customDelimiter = String.valueOf(tempArray[0].charAt(2));
                addCustomDelimiter(customDelimiter, regex);
            }

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

    //add the user defined delimiter in regex expression
    private void addCustomDelimiter(String customDelimiter, StringBuilder regex) {
        regex.append("|");
        if (isSpecialCharacter(customDelimiter)) {
            //escape the special character
            regex.append("\\");
        }
        regex.append(customDelimiter);
    }

    //returns whether custom delimiter starts with special character
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