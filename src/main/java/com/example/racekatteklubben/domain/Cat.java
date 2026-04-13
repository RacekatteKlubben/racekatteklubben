package com.example.racekatteklubben.domain;

public class Cat {
    private int catId;
    private String name;
    private String mom;
    private String dad;
    private String color;
    private Race race;
    private int memberId;

    public Cat(int catId, String catName, String momCat, String dadCat, String color, Race race, int memberId) {
        this.catId = catId;
        this.name = catName;
        this.mom = momCat;
        this.dad = dadCat;
        this.color = color;
        this.race = race;
        this.memberId = memberId;
    }

    public Cat() {
    }

    public int getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public String getMom() {
        return mom;
    }

    public String getDad() {
        return dad;
    }

    public String getColor() {
        return color;
    }

    public Race getRace() {
        return race;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setMom(String momCat) {
        this.mom = momCat;
    }

    public void setDad(String dadCat) {
        this.dad = dadCat;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
    }
}
