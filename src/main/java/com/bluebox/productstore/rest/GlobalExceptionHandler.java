package com.bluebox.productstore.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

/**
 * @author Kamran Ghiasvand
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestErrorResponse> unhandledError(Exception exception) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(exception.getMessage());
        return new ResponseEntity<>(new RestErrorResponse("unhandled_error", messages), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
