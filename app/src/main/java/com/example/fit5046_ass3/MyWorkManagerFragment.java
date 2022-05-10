package com.example.fit5046_ass3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Data;
import androidx.work.Data.Builder;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyWorkManagerFragment extends Fragment {

    private MyWorkManagerViewModel myWorkManagerViewModel;
    private Button managerBtn;

    private LiveData<List<Event>> eventLiveList;
    private ArrayList<Event> apiEventList = new ArrayList<>();


    public static MyWorkManagerFragment newInstance() {
        return new MyWorkManagerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_work_manager_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWorkManagerViewModel = new ViewModelProvider(this).get(MyWorkManagerViewModel.class);
        // TODO: Use the ViewModel

        managerBtn = getActivity().findViewById(R.id.work_manager_btn);

        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }
}