package com.flazyn.dto;

import com.flazyn.entities.User;

public class UserObjectDTO {

    private String email;
    private String profilePicture;
    private String fullName;
    private Double balance;
    private Integer age;

    public UserObjectDTO() {
    }

    public UserObjectDTO(String email, String profilePicture, String fullName, Double balance, Integer age) {
        this.email = email;
        this.profilePicture = profilePicture;
        this.fullName = fullName;
        this.balance = balance;
        this.age = age;
    }

    public UserObjectDTO(User user) {
        age = user.getAge();
        profilePicture = user.getProfilePicture();
        fullName = user.getFullName();
        balance = user.getBalance();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserObjectDTO{" +
                "email='" + email + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                '}';
    }
}
