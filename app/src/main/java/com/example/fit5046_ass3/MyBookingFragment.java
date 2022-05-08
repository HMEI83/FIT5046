package com.example.fit5046_ass3;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyBookingFragment extends Fragment {

    private MyBookingViewModel myBookingViewModel;

    private LiveData<List<UserEvent>> userEventList;

    private RecyclerView bookingRecyeclerView;
    private MybookingAdapter myBookingAdapter;



    public static MyBookingFragment newInstance() {
        return new MyBookingFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.my_booking_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myBookingViewModel = new ViewModelProvider(this).get(MyBookingViewModel.class);
        // TODO: Use the ViewModel


        MainActivity main = (MainActivity) getActivity();
        String userEmail = main.getUserEmail();

        myBookingViewModel = new ViewModelProvider(requireActivity()).get(MyBookingViewModel.class);
        bookingRecyeclerView = requireActivity().findViewById(R.id.booking_event_list);
        bookingRecyeclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myBookingAdapter = new MybookingAdapter();
        bookingRecyeclerView.setAdapter(myBookingAdapter);
        userEventList = myBookingViewModel.searchUserEventByUserEmail(userEmail);

        userEventList.observe(requireActivity(), new Observer<List<UserEvent>>() {
            @Override
            public void onChanged(List<UserEvent> userEvents) {

                int temp = myBookingAdapter.getItemCount();
                myBookingAdapter.setAllUserEventList(userEvents);
                System.out.println("ItemSize: " + temp);
                if (temp != userEvents.size()){
                    myBookingAdapter.notifyDataSetChanged();
                }
            }
        });

        myBookingAdapter.setOnItemClick(new MybookingAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(View view, int position, String eventName,
                                    String eventStartTime, String eventEndTime, String eventAddress) {


                Date beginDate = null;
                Date endDate = null;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    beginDate = format.parse(eventStartTime);
                    endDate = format.parse(eventEndTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ZoneId timeZone = ZoneId.systemDefault();
                LocalDateTime beginLocalDate = beginDate.toInstant().atZone(timeZone).toLocalDateTime();
                LocalDateTime endLocalDate = endDate.toInstant().atZone(timeZone).toLocalDateTime();


                Calendar beginTime = Calendar.getInstance();
                beginTime.set(beginLocalDate.getYear(),beginLocalDate.getMonthValue() - 1,beginLocalDate.getDayOfMonth(),
                        beginLocalDate.getHour(),beginLocalDate.getMinute(),beginLocalDate.getSecond());
                Calendar endTime = Calendar.getInstance();
                endTime.set(endLocalDate.getYear(),endLocalDate.getMonthValue() - 1,endLocalDate.getDayOfMonth(),
                        endLocalDate.getHour(),endLocalDate.getMinute(),endLocalDate.getSecond());

                System.out.println("year" +beginLocalDate.getYear() + "Month"+ beginLocalDate.getMonthValue() + "Day" + beginLocalDate.getDayOfMonth());
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, eventName);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,eventAddress);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,beginTime.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime.getTimeInMillis());
                //intent.putExtra(CalendarContract.Events.ALL_DAY,false);
                startActivity(intent);
//                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(requireActivity(),"Failed",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

}