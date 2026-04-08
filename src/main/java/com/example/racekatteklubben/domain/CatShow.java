package com.example.racekatteklubben.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CatShow {
    private int catShowId;
    private Member member;
    private LocalDateTime date;
    private List<Cat> cats;

    public CatShow(int catShowId, Member member, LocalDateTime date, List<Cat> cats) {
        this.catShowId = catShowId;
        this.member = member;
        this.date = date;
        this.cats = cats;
    }

    public int getCatShowId() {
        return catShowId;
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}
