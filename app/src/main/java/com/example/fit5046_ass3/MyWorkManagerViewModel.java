package com.example.fit5046_ass3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyWorkManagerViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository eventRepository;

    public MyWorkManagerViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
    }

    public LiveData<List<Event>> getAllEventLive() {
        return eventRepository.getAllEventLive();
    }

}