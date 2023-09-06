package com.ra.model;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean status;
    private int role;
    private double balance;
    private Date birthday;

    public User(String id, String username, String password, boolean status, int role, double balance, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.balance = balance;
        this.birthday = birthday;
    }

    public User() {
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
