package com.example.racekatteklubben.domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberId;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private List<Cat> cats;

    public Member(int memberId, String name, String password, String email, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cats = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}
