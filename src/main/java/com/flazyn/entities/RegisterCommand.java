package com.flazyn.entities;

public class RegisterCommand {
    private String username;
    private String password;

    public RegisterCommand() {
    }

    public RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
