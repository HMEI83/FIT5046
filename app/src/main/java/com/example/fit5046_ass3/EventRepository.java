package com.example.fit5046_ass3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private LiveData<List<Event>>allEventLive;
    private EventDao eventDao;

    public EventRepository(Context context) {
        EventDatabase eventDatabase = EventDatabase.getDatabase(context.getApplicationContext());
        eventDao = eventDatabase.getEventDao();
        allEventLive = eventDao.getAllEvent();
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

    public LiveData<List<Event>> searchEventById(int id) {

        return eventDao.searchEventById(id);
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
