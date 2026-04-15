package com.example.racekatteklubben.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CatShow {
    private int catShowId;
    private String title;
    private String location;
    private LocalDateTime date;

    public CatShow(int catShowId, String title, String location, LocalDateTime date) {
        this.catShowId = catShowId;
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public CatShow() {
    }

    public int getCatShowId() {
        return catShowId;
    }

    public void setCatShowId(int catShowId) {
        this.catShowId = catShowId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}