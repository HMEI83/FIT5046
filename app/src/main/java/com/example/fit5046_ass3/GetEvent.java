package com.example.fit5046_ass3;


import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetEvent {

    JSONArray jarray;
    ArrayList<Event> eventList;

    public GetEvent(){
        jarray = new JSONArray();
        eventList = new ArrayList<Event>();
    }

    public JSONArray getJarray(){
        return jarray;
    }

    public ArrayList<Event> getEventList(){
        return eventList;
    }


    public void getApiData() throws Exception{

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String password = "funwithevent:ckytmr5q6w35";
//        String password = "";
        byte[] data = password.getBytes("UTF-8");
        String key = Base64.encodeToString(data, Base64.URL_SAFE | Base64.NO_WRAP);

        StringBuilder requestURL = new StringBuilder("https://api.eventfinda.com.au/v2/events.json?");
        requestURL.append("fields=event:(url,name,description,sessions,point,datetime_start,datetime_end,address,images,category),session:(timezone,datetime_start)");
        requestURL.append("&order=date&row=20&location=4");

        Request request = new Request.Builder()
                .url(requestURL.toString())
                .method("GET", null)
                .addHeader("Authorization", "Basic " + key)
                .build();


        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String newsResponse = response.body().string();

                try {
                    JSONObject newsObject = new JSONObject(newsResponse);
                    jarray = newsObject.getJSONArray("events");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        String eventUrl = object.getString("url");
                        String eventName = object.getString("name");
                        String eventDesc = object.getString("description");
                        JSONObject location = object.getJSONObject("point");
                        String startTime = object.getString("datetime_start");
                        String endTime = object.getString("datetime_end");
                        String address = object.getString("address");
                        //String price = object.getJSONObject("sessions").getJSONArray("sessions").getJSONObject(0).getJSONObject("session_tickets").getJSONArray("session_tickets").getJSONObject(0).getString("price");
                        String price = "FREE";//final String imageUrl;
                        String category = object.getJSONObject("category").getString("name");
                        JSONArray imageArray = object.getJSONObject("images").getJSONArray("images").getJSONObject(0).getJSONObject("transforms").getJSONArray("transforms");

                        Event event = new Event(i,eventName,startTime);
                        eventList.add(event);
                        System.out.println(eventList.size());

                        Event event = new Event(i,eventName,startTime);
                        eventList.add(event);
                        System.out.println(eventList.size());

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
