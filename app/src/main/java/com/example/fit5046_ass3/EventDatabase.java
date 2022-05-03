package com.example.fit5046_ass3;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase{
    private static EventDatabase INSTANCE;
    static synchronized EventDatabase getDatabase(Context context){
        if (INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class,"event_database")
            .build();
        }
        return INSTANCE;
    }
    public abstract EventDao getEventDao();
}
