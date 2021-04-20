package com.myapplicationdev.android.p02_holiday;

public class Events {
    private String eventType;
    private String eventName;
    private String imageName;
    private String eventDate;

    Events(String eventType, String eventName, String imageName, String eventDate) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.imageName = imageName;
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
