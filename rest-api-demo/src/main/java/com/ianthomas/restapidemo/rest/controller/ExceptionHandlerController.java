package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.exception.*;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseDto handleInvalidInputException(HttpServletRequest req, InvalidInputException ex){
        LOG.warn("Invalid Input Exception : {}",ex.getMessage());
        return new ResponseDto("error", ex.getMessage());      // Returns error response
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseDto handleItemAlreadyExistsException(HttpServletRequest req, ItemAlreadyExistsException ex){
        LOG.warn("Item Already Exists Exception: {}",ex.getMessage());
        return new ResponseDto("error", ex.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseDto handleItemNotFoundException(HttpServletRequest req, ItemNotFoundException ex){
        LOG.warn("Item Not Found Exception : {}",ex.getMessage());
        return new ResponseDto("error", ex.getMessage());
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseDto handleInvalidArgumentsException(HttpServletRequest req, InvalidArgumentsException ex){
        LOG.warn("Invalid Arguments Exception : {}",ex.getMessage());
        return new ResponseDto("error", ex.getMessage());
    }
}
