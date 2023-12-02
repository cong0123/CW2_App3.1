package com.example.cw2_app31;

public class DataModel {
    private String name;
    private String dob;
    private String email;
    private int imageResourceId;

    public DataModel(String name, String dob, String email, int imageResourceId) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}