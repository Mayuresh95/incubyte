package io.incubyte;

public class StringCalculator {
    final String DECLARE_DELIMITER = "//";

    int add(String numbers) throws StringCalculatorException {
        int result = 0;

        if(numbers == null || numbers.length() == 0) {
            return 0;
        }

        StringBuilder regex = new StringBuilder("[\\n,");
        String[] literals;

        if (numbers.startsWith(DECLARE_DELIMITER)) {
            String[] tempArray = numbers.split("\\n", 2);
            regex.append(tempArray[0].charAt(2));
            numbers = tempArray[1];
        }

        regex.append("]+");
        literals = numbers.split(regex.toString());

        for (String literal : literals) {
            result = result + Integer.parseInt(literal.trim());
        }

        return result;
    }
}
