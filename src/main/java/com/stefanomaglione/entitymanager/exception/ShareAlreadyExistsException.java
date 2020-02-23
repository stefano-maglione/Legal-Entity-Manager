package com.stefanomaglione.entitymanager.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ShareAlreadyExistsException extends RuntimeException {
    public ShareAlreadyExistsException(String s) {
        super(s);
    }
}

