package com.example.fit5046_ass3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "event_name")
    private String eventName;
    @ColumnInfo(name = "event_description")
    private String eventDescription;
    @ColumnInfo(name = "event_category")
    private String eventCategory;
    @ColumnInfo(name = "event_start_time")
    private String eventStartTime;
    @ColumnInfo(name = "event_end_time")
    private String eventEndTime;
    @ColumnInfo(name = "event_address")
    private String eventAddress;
    @ColumnInfo(name = "event_lat")
    private String eventLat;
    @ColumnInfo(name = "event_lng")
    private String eventLng;

    public Event(String eventName, String eventDescription, String eventCategory,
                 String eventStartTime, String eventEndTime, String eventAddress,
                 String eventLat, String eventLng) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventCategory = eventCategory;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventAddress = eventAddress;
        this.eventLat = eventLat;
        this.eventLng = eventLng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
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

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventLat() {
        return eventLat;
    }

    public void setEventLat(String eventLat) {
        this.eventLat = eventLat;
    }

    public String getEventLng() {
        return eventLng;
    }

    public void setEventLng(String eventLng) {
        this.eventLng = eventLng;
    }
}
