package com.example.racekatteklubben.domain;

public class Cat {
    private int catId;
    private String name;
    private String mom;
    private String dad;
    private String color;
    private Race race;
    private int memberId;
    private byte[] image;

    public Cat(int catId, String catName, String momCat, String dadCat, String color, Race race, int memberId, byte[] image) {
        this.catId = catId;
        this.name = catName;
        this.mom = momCat;
        this.dad = dadCat;
        this.color = color;
        this.race = race;
        this.memberId = memberId;
        this.image = image;
    }

    public Cat() {
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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
        this.memberId = memberId;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
}
