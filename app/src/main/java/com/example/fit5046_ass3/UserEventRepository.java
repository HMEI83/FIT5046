package com.example.fit5046_ass3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserEventRepository {

    private LiveData<List<UserEvent>> allUserEventLive;
    private UserEventDao userEventDao;

    public UserEventRepository(Context context) {
        UserEventDatabase userEventDatabase = UserEventDatabase.getDatabase(context.getApplicationContext());
        userEventDao = userEventDatabase.getUserEventDao();
        allUserEventLive = userEventDao.getAllUserEvent();
    }

    public LiveData<List<UserEvent>> getAllUserEventLive() {
        return allUserEventLive;
    }

    public LiveData<List<UserEvent>> searchUserEventByUserEmail(String userEmail){
        return userEventDao.searchUserEventByUserEmail(userEmail);
    }

    void insertUserEvent(UserEvent... userEvents){
        new UserEventRepository.InsertAsyncTask(userEventDao).execute(userEvents);
    }

    static class InsertAsyncTask extends AsyncTask<UserEvent,Void,Void> {
        private UserEventDao userEventDao;

        public InsertAsyncTask(UserEventDao userEventDao) {
            this.userEventDao = userEventDao;
        }

        @Override
        protected Void doInBackground(UserEvent... userEvents) {
            userEventDao.insertUserEvent(userEvents);
            return null;
        }
    }

}
