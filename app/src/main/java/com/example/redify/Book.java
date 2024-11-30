package com.example.redify;

public class Book {
    private String title;
    private String author;
    private String date;
    private int image;
    private String description;
    private boolean favorite;
    private boolean newness;
    private float calification;

    public Book(String title, String author, String date, int image, String description, boolean favorite, boolean newness, float calification) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.image = image;
        this.description = description;
        this.favorite = favorite;
        this.newness = newness;
        this.calification = calification;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getCalification() {
        return calification;
    }

    public void setFavoriteTrue(){
        this.favorite = true;
    }

    public void setNewnessTrue(){
        this.newness = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isNewness() {
        return newness;
    }

    public void setNewness(boolean newness) {
        this.newness = newness;
    }

    public float isCalification() {
        return calification;
    }

    public void setCalification(float calification) {
        this.calification = calification;
    }
}
