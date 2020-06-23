package io.incubyte;

public class StringCalculatorException extends Exception {
    private String msg;

    public StringCalculatorException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
