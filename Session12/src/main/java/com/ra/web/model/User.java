package com.ra.web.model;

public class User {
    private String username;
    private String phone;
    private boolean gender;

    public User(String username, String phone, boolean gender) {
        this.username = username;
        this.phone = phone;
        this.gender = gender;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
