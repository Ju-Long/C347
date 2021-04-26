package com.myapplicationdev.android.lp1practical;

public class Task {

    private String description;
    private String date;

    public Task(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
