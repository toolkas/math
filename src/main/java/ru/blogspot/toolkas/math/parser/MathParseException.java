package ru.blogspot.toolkas.math.parser;

public class MathParseException extends Exception {
    public MathParseException() {
    }

    public MathParseException(String message) {
        super(message);
    }

    public MathParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MathParseException(Throwable cause) {
        super(cause);
    }

    public MathParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
