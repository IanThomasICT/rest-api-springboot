package com.ianthomas.restapidemo.exception;

public class ItemAlreadyExistsException extends ApplicationException {

    private static final long serialVersionUID = 6286458492858141404L;

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

    public ItemAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
