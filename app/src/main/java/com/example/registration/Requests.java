package com.example.registration;

public class Requests {

    private String number;
    private String comment;
    private int longitude;
    private int attribute;

    public Requests(){

    }

    public Requests(String number,String comment ){

        this.number=number;
        this.comment=comment;
        this.longitude=Constants.longitude;
        this.attribute=Constants.attribute;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) { this.attribute = attribute; }
}

