package com.example.fit5046_ass3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    private String userEmail;

    private EventRepository eventRepository;


    private GetEvent getEvent = new GetEvent();
    private ArrayList<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //navController = Navigation.findNavController(findViewById(R.id.fragement));
        //NavigationUI.setupActionBarWithNavController(this,navController);

        try {
            getEvent.getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userEmail = getIntent().getStringExtra("userEmail");

        WorkManager myWorkManager = WorkManager.getInstance(this);
        //LiveData<List<Event>> eventLiveList =;

        ArrayList<String> eventNameList = new ArrayList<>();

        eventNameList.add("AA");
        eventNameList.add("BB");

//        eventLiveList.observe(this, new Observer<List<Event>>() {
//            @Override
//            public void onChanged(List<Event> events) {
//                for(int i = 0; i < events.size();i++){
//                    eventNameList.add(events.get(i).getEventName());
//                }
//            }
//        });
        String newEventList = "";

        for(int a = 0; a < eventNameList.size(); a++){
            for(int x = 0; x < eventList.size(); x++){
                if(!eventNameList.get(a).equals(eventList.get(x).getEventName())){
                    newEventList = eventList.get(x).getEventName();
                    break;
                }
            }
        }

        System.out.println("2222222222222222222222" + newEventList);

        Data.Builder builder = new Data.Builder();
        builder.putString("event",newEventList);
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorkManager.class,15, TimeUnit.MINUTES)
                .setInputData(builder.build())
                .build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);


    }

    public String getUserEmail(){
        return userEmail;
    }

    public ArrayList<Event> getEventList() {
        eventList = getEvent.getEventList();
        System.out.println(eventList.size());
        return  eventList;
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }


}