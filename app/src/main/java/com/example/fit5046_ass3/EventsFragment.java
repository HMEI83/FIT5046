package com.example.fit5046_ass3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {
    private EventViewModel eventViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Event>>searchEvents;

    private ArrayList<Event> eventList;

    public EventsFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchEvent).getActionView();
        searchView.setMaxWidth(500);


        MainActivity main = (MainActivity) getActivity();
        eventList = main.getEventList();

        System.out.println("Option Finish");

        eventViewModel.clearEvent();
        insertEventData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String pattern = s.trim();
                searchEvents.removeObservers(requireActivity());
                searchEvents = eventViewModel.searchEventByName(pattern);
                searchEvents.observe(requireActivity(), new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> events) {
                        int temp = adapter.getItemCount();
                        adapter.setAllEvent(events);
                        if (temp != events.size()){
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController;
        switch (item.getItemId()){
            case R.id.myBooking:
                navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
                navController.navigate(R.id.myBookingFragment);
                return true;
            case R.id.myWorkManager:
                navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
                navController.navigate(R.id.myWorkManagerFragment);
                return true;
            case R.id.myGraphic:
                navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
                navController.navigate(R.id.eventGraphFragment);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void insertEventData(){
        for(int i = 0; i < eventList.size(); i++){
            eventViewModel.insertEvent(eventList.get(i));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        searchEvents = eventViewModel.getAllEventLive();
        searchEvents.observe(requireActivity(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                int temp = adapter.getItemCount();
                adapter.setAllEvent(events);
                if (temp != events.size()){
                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter.setOnItemClick(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String eventName,String eventDescription, String eventCategory,
                                    String eventStartTime, String eventEndTime, String eventAddress,
                                    String eventLat, String eventLng) {
                Context context = view.getContext();

                Bundle bundle = new Bundle();

                bundle.putInt("detailPosition",position);
                bundle.putString("detailName", eventName);
                bundle.putString("detailDescription", eventDescription);
                bundle.putString("detailEventCategory",eventCategory);
                bundle.putString("detailEventStartTime",eventStartTime);
                bundle.putString("detailEventEndTime",eventEndTime);
                bundle.putString("detailEventAddress",eventAddress);

                bundle.putString("detailEventLat",eventLat);
                bundle.putString("detailEventLng",eventLng);


                 NavController controller = Navigation.findNavController(view);
                 controller.navigate(R.id.action_eventsFragment_to_eventDetailFragment,bundle);

            }
        });


        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(),eventList.get(0).getEventName(), Toast.LENGTH_SHORT).show();

                eventViewModel.clearEvent();
                insertEventData();
                System.out.println("Button Finish");



//                NavController navController = Navigation.findNavController(view);
//                navController.navigate(R.id.action_eventsFragment_to_addEventFragment);

            }
        });
    }

    @Override
    public void onResume() {
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromInputMethod(getView().getWindowToken(),0);
        super.onResume();
    }
}