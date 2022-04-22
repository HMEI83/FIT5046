package com.example.fit5046_ass3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EventsFragment extends Fragment {
    private EventViewModel eventViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private FloatingActionButton floatingActionButton;

    public EventsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        eventViewModel.getAllEventLive().observe(requireActivity(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                int temp = adapter.getItemCount();
                adapter.setAllEvent(events);
                if (temp != events.size()){
                    adapter.notifyDataSetChanged();
                }
            }
        });
        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_eventsFragment_to_addEventFragment);
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