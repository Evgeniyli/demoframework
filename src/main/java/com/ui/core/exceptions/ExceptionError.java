package com.ui.core.exceptions;

public class ExceptionError extends Error {
    private final String errorMessage;
    private Throwable errorCause;


    /**
     * Constructor a new error with the specified detail message and cause
     *
     * @param message the detail message. The detail message is saved for
     * @param cause   is saved for any reasons by report
     */
    public ExceptionError(String message, Throwable cause) {
        super(message, cause);
        errorMessage = message;
        errorCause = cause;
    }

    public ExceptionError(String message) {
        super(message);
        errorMessage = message;
    }

    @Override
    public String getMessage() {
        if (errorCause != null) {
            return errorMessage + errorCause;
        }
        return errorMessage;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
