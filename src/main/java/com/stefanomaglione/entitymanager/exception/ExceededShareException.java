package com.stefanomaglione.entitymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceededShareException extends RuntimeException {
    public ExceededShareException(String s) {
        super(s);
    }
}