package com.flazyn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }


    public ApiError withMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiError build() {
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
