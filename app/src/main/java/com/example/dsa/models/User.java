package com.example.dsa.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private int money;

    public User(String username, String fullName, String email, int money, int id) {
        this.id = id;
        this.username = username;
        this.password = null;
        this.fullName = fullName;
        this.email = email;
        this.money = money;
    }

    public User(String password,  int id) {
        this.id = id;
        this.username = null;
        this.password = password;
        this.fullName = null;
        this.email = null;
        this.money = 0;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;}
}
