package com.example.fit5046_ass3;
import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends AndroidViewModel{
    private EventRepository eventRepository;

    
    public EventViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);

    }

    public LiveData<List<Event>> getAllEventLive() {

        return eventRepository.getAllEventLive();
    }
    /*
    public void getEventData(EventModel eventModel){
        if(eventModel == null || eventModel.getCode()==50001){
            Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show();
        }
    }*/




    public LiveData<List<Event>> searchEventByName(String pattern){
        return eventRepository.searchEventByName(pattern);
    }

    void insertEvent(Event... events){
        eventRepository.insertEvent(events);
    }

    void clearEvent(Event... events){
        eventRepository.clearEvent();
    }

}
