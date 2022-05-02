package com.example.fit5046_ass3;

import androidx.room.ColumnInfo;

import java.io.Serializable;
import java.util.List;

public class EventModel implements Serializable {
    private String status;
    private int code;
    private String message;
    private List<EventBean> eventBeanList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EventBean> getEventBeanList() {
        return eventBeanList;
    }

    public void setEventBeanList(List<EventBean> eventBeanList) {
        this.eventBeanList = eventBeanList;
    }

    public static class EventBean{
        private int id;
        private String eventName;
        private String eventTime;
        private String eventUrl;
        private String eventDesc;
        //private String location;
        private String startTime;
        private String endTime;
        private String address;
        private String price;
        private String category;
        //private String imageArray;


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
    }
}
