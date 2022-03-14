package com.ianthomas.restapidemo.rest.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {

    private static final long serialVersionUID = 6936074936467382843L;
    private String status;
    private String message;
    private Object result;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
