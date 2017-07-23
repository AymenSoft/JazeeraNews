package com.aymensoft.jazeeranews.model;

public class News {

    private String author;
    private String title;
    private String urlToImage;

    public News(String author, String title, String urlToImage) {
        this.author = author;
        this.title = title;
        this.urlToImage = urlToImage;
    }

    public News(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Override
    public String toString() {
        return "News{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }
}
