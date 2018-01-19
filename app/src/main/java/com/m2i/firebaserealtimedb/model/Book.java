package com.m2i.firebaserealtimedb.model;

/**
 * Created by Formation on 19/01/2018.
 */

public class Book {
    private String title;
    private Double price;
    private Author author;


    public Book() {
    }

    public Book(String title, Double price, Author author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Book setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
