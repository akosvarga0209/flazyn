package com.flazyn.dto;

public class GeneralMessageDTO {

    private String message;

    public GeneralMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
