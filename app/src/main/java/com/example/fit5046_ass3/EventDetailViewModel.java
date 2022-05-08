package com.example.fit5046_ass3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EventDetailViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository eventRepository;
    private UserEventRepository userEventRepository;


    public EventDetailViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        userEventRepository = new UserEventRepository(application);
    }


    public LiveData<List<Event>> searchEventById(int id){
        return eventRepository.searchEventById(id);
    }

    public LiveData<List<UserEvent>> getAllUserEventLive() {
        return userEventRepository.getAllUserEventLive();
    }

    void insertUserEvent(UserEvent... userEvents){
        userEventRepository.insertUserEvent(userEvents);
    }




}