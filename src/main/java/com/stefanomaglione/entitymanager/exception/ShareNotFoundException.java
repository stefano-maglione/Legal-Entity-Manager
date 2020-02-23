package com.stefanomaglione.entitymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShareNotFoundException extends RuntimeException {
    public ShareNotFoundException(String s) {
        super(s);
    }
}