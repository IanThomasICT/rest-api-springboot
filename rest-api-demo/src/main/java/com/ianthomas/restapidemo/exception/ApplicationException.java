package com.ianthomas.restapidemo.exception;

import java.io.Serializable;

public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -325759265578058770L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
