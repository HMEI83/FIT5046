package com.example.fit5046_ass3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

<<<<<<< Updated upstream
=======
import org.json.JSONArray;

import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.List;

public class EventRepository {

    private LiveData<List<Event>>allEventLive;
    private EventDao eventDao;
<<<<<<< Updated upstream
=======

    private GetEvent getEvent;
    private ArrayList<Event> eventList;

>>>>>>> Stashed changes
    public EventRepository(Context context) {
        EventDatabase eventDatabase = EventDatabase.getDatabase(context.getApplicationContext());
        eventDao = eventDatabase.getEventDao();
        allEventLive = eventDao.getAllEvent();
    }

    public void setEventListFromApi(){
        ArrayList<Event> eventList = getEvent.getEventList();

        for(int i = 0; i < eventList.size(); i++){
            //insertEvent(eventList.get(i));
            System.out.println(eventList.size());
        }

    }


    public LiveData<List<Event>> getAllEventLive() {
        return allEventLive;
    }

    public LiveData<List<Event>> searchEventByName(String pattern){
        return eventDao.searchEventByName("%" + pattern + "%");
    }

    void insertEvent(Event... events){
        new InsertAsyncTask(eventDao).execute(events);
    }

    void clearEvent(Event... events){
        new ClearAsyncTask(eventDao).execute();
    }



    static class InsertAsyncTask extends AsyncTask<Event,Void,Void> {
        private EventDao eventDao;

        public InsertAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insertEvent(events);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        private EventDao eventDao;

        public ClearAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.deleteAllEvent();
            return null;
        }
    }
}
