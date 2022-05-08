package com.example.fit5046_ass3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEvent {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userEvent_userEmail")
    private String userEmail;

    @ColumnInfo(name = "userEvent_eventId")
    private String eventId;

    @ColumnInfo(name = "userEvent_name")
    private String eventName;

    @ColumnInfo(name = "userEvent_eventStartTime")
    private String eventStartTime;

    @ColumnInfo(name = "userEvent_eventEndTime")
    private String eventEndTime;

    @ColumnInfo(name = "userEvent_eventLocation")
    private String eventLocation;

    public UserEvent(String userEmail, String eventId, String eventName, String eventStartTime, String eventEndTime, String eventLocation) {
        this.userEmail = userEmail;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventLocation = eventLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
