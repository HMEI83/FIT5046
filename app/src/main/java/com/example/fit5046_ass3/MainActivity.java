package com.example.fit5046_ass3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    private GetEvent getEvent = new GetEvent();
    private ArrayList<Event> eventList;

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

    }

    public void setGetEvent(){
        eventList = getEvent.getEventList();
        System.out.println(eventList.size());
    }

    public ArrayList<Event> getEventList(){
        return eventList;
    }



    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }


}