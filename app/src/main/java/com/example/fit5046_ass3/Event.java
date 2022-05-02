package com.example.fit5046_ass3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "event_name")
    private String eventName;
    @ColumnInfo(name = "event_time")
    private String eventTime;
    @ColumnInfo(name = "event_url")
    private String eventUrl;
    @ColumnInfo(name = "event_desc")
    private String eventDesc;
    @ColumnInfo(name = "event_location")
    private String location;
    @ColumnInfo(name = "event_start_time")
    private String startTime;
    @ColumnInfo(name = "event_end_time")
    private String endTime;
    @ColumnInfo(name = "event_address")
    private String address;
    @ColumnInfo(name = "event_price")
    private String price;
    @ColumnInfo(name = "event_category")
    private String category;
    @ColumnInfo(name = "event_image")
    private String imageArray;

    public Event(int id, String eventName, String eventTime, String eventUrl, String eventDesc, String location, String startTime, String endTime, String address, String price, String category, String imageArray) {
        this.id = id;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventUrl = eventUrl;
        this.eventDesc = eventDesc;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.price = price;
        this.category = category;
        this.imageArray = imageArray;
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

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageArray() {
        return imageArray;
    }

    public void setImageArray(String imageArray) {
        this.imageArray = imageArray;
    }
}
