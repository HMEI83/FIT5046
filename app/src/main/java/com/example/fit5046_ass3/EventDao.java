package com.example.fit5046_ass3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insertEvent(Event... events);

    @Update
    void updateEvent(Event... events);

    @Delete
    void deleteEvent(Event... events);

    @Query("DELETE FROM EVENT")
    void deleteAllEvent();

    @Query("SELECT * FROM EVENT")
    LiveData<List<Event>>getAllEvent();

    @Query("SELECT * FROM EVENT WHERE event_name LIKE :pattern ORDER BY ID DESC")
    LiveData<List<Event>>searchEventByName(String pattern);

    @Query("SELECT * FROM EVENT WHERE ID IS :id")
    LiveData<List<Event>>searchEventById(int id);
}
