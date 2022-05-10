package com.example.fit5046_ass3;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.common.internal.Constants;

import java.util.ArrayList;

public class MyWorkManager extends Worker {


    public MyWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {


        String eventName = getInputData().getString("event");

        Log.d("Hello","Start");

        Log.d("Hello","Start insert"+eventName + "1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Hello","End");

        return Result.success();
    }
}
