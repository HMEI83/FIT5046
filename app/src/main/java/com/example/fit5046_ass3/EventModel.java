package com.example.fit5046_ass3;

import androidx.room.ColumnInfo;

import java.io.Serializable;
import java.util.List;

public class EventModel implements Serializable {
    //private String status;
    //private int code;
    //private String message;
    private List<EventBean> eventBeanList;

    public List<EventBean> getEventBeanList() {
        return eventBeanList;
    }

    public void setEventBeanList(List<EventBean> eventBeanList) {
        this.eventBeanList = eventBeanList;
    }

    public static class EventBean{
        private int id;
        private String name;
        private String datetime_start;
        private String datetime_end;
        private String eventUrl;
        private String eventDesc;
        //private String location;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatetime_start() {
            return datetime_start;
        }

        public void setDatetime_start(String datetime_start) {
            this.datetime_start = datetime_start;
        }

        public String getDatetime_end() {
            return datetime_end;
        }

        public void setDatetime_end(String datetime_end) {
            this.datetime_end = datetime_end;
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
