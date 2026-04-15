package com.example.racekatteklubben.domain;
import java.time.LocalDateTime;

public class CatShowRegistration {
    private int registrationId;
    private int catId;
    private int catShowId;
    private LocalDateTime registrationDate;
    private String status;

    public CatShowRegistration(int registrationId, int catId, int catShowId, LocalDateTime registrationDate, String status) {
        this.registrationId = registrationId;
        this.catId = catId;
        this.catShowId = catShowId;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public CatShowRegistration() {
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCatShowId() {
        return catShowId;
    }

    public void setCatShowId(int catShowId) {
        this.catShowId = catShowId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}