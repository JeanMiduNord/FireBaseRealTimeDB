package com.m2i.firebaserealtimedb.model;

/**
 * Created by Formation on 19/01/2018.
 */

public class Author {
        private String name;
        private String firstName;
        private String nationality;

    public Author() {
    }

    public Author(String name, String firstName, String nationality) {
        this.name = name;
        this.firstName = firstName;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Author setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }
}
