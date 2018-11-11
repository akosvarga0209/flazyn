package com.flazyn.controller;

import com.flazyn.dto.ApiError;
import com.flazyn.exception.GeneralServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        if(e instanceof GeneralServerErrorException) {
            return mapToApiError(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return mapToApiError(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity mapToApiError(Exception e, HttpStatus httpStatus) {
        ApiError error = new ApiError()
                .withMessage(e.getMessage())
                .withStatus(httpStatus);
        return new ResponseEntity<Object>(error, error.getStatus());
    }

}
