package com.example.bookreader;

public class Book {

    private int id, page;
    private String name, author, imageUrl, shortDescription, longDescription;
    private boolean isExpand;

    public Book(int id, int page, String name, String author, String imageUrl, String shortDescription, String longDescription) {
        this.id = id;
        this.page = page;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        isExpand = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isExpand() {
        return isExpand;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", page=" + page +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
