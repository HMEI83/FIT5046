package com.example.fit5046_ass3;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEvent.class}, version = 1, exportSchema = false)
public abstract class UserEventDatabase extends RoomDatabase {
    private static UserEventDatabase INSTANCE;
    static synchronized UserEventDatabase getDatabase(Context context){
        if (INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserEventDatabase.class,"userEvent_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract UserEventDao getUserEventDao();


}
