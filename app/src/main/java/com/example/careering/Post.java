package com.example.careering;

public class Post{

    private String name;
    private String company;
    private String publisherName;
    private String description;
    private String userID;

    public Post(){

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    public String getPublisherName() {
        return publisherName;
    }


    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
