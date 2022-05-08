package com.example.fit5046_ass3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyBookingViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository eventRepository;
    private UserEventRepository userEventRepository;


    public MyBookingViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        userEventRepository = new UserEventRepository(application);
    }


    public LiveData<List<Event>> searchEventById(int id){
        return eventRepository.searchEventById(id);
    }

    public LiveData<List<UserEvent>> searchUserEventByUserEmail(String userEmail){
        return userEventRepository.searchUserEventByUserEmail(userEmail);
    }


    public LiveData<List<UserEvent>> getAllUserEventLive() {
        return userEventRepository.getAllUserEventLive();
    }


}