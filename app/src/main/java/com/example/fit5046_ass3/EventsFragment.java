package com.example.fit5046_ass3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EventsFragment extends Fragment {
    private EventViewModel eventViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Event>>searchEvents;
    //private JSONArray jarray;
    private GetEvent getEvent;


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

            case R.id.myEvent:
                navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
                navController.navigate(R.id.myEventFragment);
                return true;

            case R.id.editProfile:
                navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
                navController.navigate(R.id.myProfileFragment);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            Request();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_eventsFragment_to_addEventFragment);
            }
        });
    } */


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //ArrayList eventsList = new ArrayList();
        super.onActivityCreated(savedInstanceState);
        GetEvent getEvent = new GetEvent();
        try {
            getEvent.getEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_eventsFragment_to_addEventFragment);
            }
        });
    }

/*
    public void getData(EventModel eventModel){
        if (eventModel == null){
            Toast.makeText(requireActivity(),"failure",Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new Adapter(eventModel.getEventBeanList(),this);
        recyclerView.setAdapter(adapter);
    }

    public void Request() throws UnsupportedEncodingException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String password = "funwithevent:ckytmr5q6w35";
        //        String password = "";
        byte[] data = password.getBytes("UTF-8");
        String key = android.util.Base64.encodeToString(data, Base64.URL_SAFE | Base64.NO_WRAP);

        StringBuilder requestURL = new StringBuilder("https://api.eventfinda.com.au/v2/events.json?");
        requestURL.append("fields=event:(url,name,description,sessions,point,datetime_start,datetime_end,address,images,category),session:(timezone,datetime_start)");
        requestURL.append("&order=date&row=20&location=4");

        Request request = new Request.Builder()
                .url(requestURL.toString())
                .method("GET", null)
                .addHeader("Authorization", "Basic " + key)
                .build();

        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String newsResponse = response.body().string();
                Gson gson = new Gson();
                final EventModel eventModel = gson.fromJson(newsResponse,EventModel.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getData(eventModel);
                    }
                });

            }
        });
    }
*/

    @Override
    public void onResume() {
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromInputMethod(getView().getWindowToken(),0);
        super.onResume();
    }
}