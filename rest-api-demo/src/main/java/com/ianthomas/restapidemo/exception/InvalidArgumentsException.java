package com.ianthomas.restapidemo.exception;

public class InvalidArgumentsException extends ApplicationException{
    private static final long serialVersionUID = -2644906959582641677L;

    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentsException(Throwable cause) {
        super(cause);
    }
}
