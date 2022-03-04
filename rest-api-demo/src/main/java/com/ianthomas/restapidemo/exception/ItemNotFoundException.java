package com.ianthomas.restapidemo.exception;

public class ItemNotFoundException extends ApplicationException {

    private static final long serialVersionUID = -3370068869181423216L;

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
