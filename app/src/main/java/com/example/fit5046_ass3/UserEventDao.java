package com.example.fit5046_ass3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserEventDao {
    @Insert
    void insertUserEvent(UserEvent... userEvents);

    @Query("SELECT * FROM USEREVENT")
    LiveData<List<UserEvent>>getAllUserEvent();

    @Query("SELECT * FROM USEREVENT WHERE USEREVENT_USEREMAIL IS :userEmail")
    LiveData<List<UserEvent>> searchUserEventByUserEmail(String userEmail);
}
