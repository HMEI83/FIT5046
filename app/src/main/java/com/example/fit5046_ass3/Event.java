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
//    @ColumnInfo(name = "event_url")
//    private String eventUrl;
//    @ColumnInfo(name = "event_desc")
//    private String eventDesc;
//    @ColumnInfo(name = "event_location")
//    private String location;
//    @ColumnInfo(name = "event_start_time")
//    private String startTime;
//    @ColumnInfo(name = "event_end_time")
//    private String endTime;
//    @ColumnInfo(name = "event_address")
//    private String address;
//    @ColumnInfo(name = "event_price")
//    private String price;
//    @ColumnInfo(name = "event_category")
//    private String category;
//    @ColumnInfo(name = "event_image")
//    private String imageArray;


    public Event(int id, String eventName, String eventTime) {
        this.id = id;
        this.eventName = eventName;
        this.eventTime = eventTime;
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
}
