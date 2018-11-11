package com.flazyn.exception;

public class GeneralServerErrorException extends Exception {

    public GeneralServerErrorException() {
        super("Internal server error");
    }

}
