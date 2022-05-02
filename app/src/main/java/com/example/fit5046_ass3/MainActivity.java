package com.example.fit5046_ass3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private GetEvent getEvent;
    private ArrayList<Event> eventArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getEvent.getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setEventArrayList();
        //navController = Navigation.findNavController(findViewById(R.id.fragement));
        //NavigationUI.setupActionBarWithNavController(this,navController);
    }

    public void setEventArrayList(){
        eventArrayList = getEvent.getEventList();
        System.out.println(eventArrayList.size());
    }


    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }


}