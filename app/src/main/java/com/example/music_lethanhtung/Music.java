package com.example.music_lethanhtung;

public class Music {
    private String name;
    private String author;
    private int mp3;
    private int image;
    private int rateBar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRateBar() {
        return rateBar;
    }

    public void setRateBar(int rateBar) {
        this.rateBar = rateBar;
    }

    public Music(String name, String author, int mp3, int image, int rateBar) {
        this.name = name;
        this.author = author;
        this.mp3 = mp3;
        this.image = image;
        this.rateBar = rateBar;
    }
}