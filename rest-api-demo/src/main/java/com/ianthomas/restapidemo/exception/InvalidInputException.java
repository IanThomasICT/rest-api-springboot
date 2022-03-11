package com.ianthomas.restapidemo.exception;

public class InvalidInputException extends ApplicationException {

    private static final long serialVersionUID = -7410399020886610359L;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
