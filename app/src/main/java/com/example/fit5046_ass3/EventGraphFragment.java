package com.example.fit5046_ass3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGraphFragment extends Fragment {

    private EventGraphViewModel eventGraphViewModel;
    private HashMap<String,Integer> eventCategoryMap;
    //private HashMap<String,Integer> eventMap;

    private PieChart pieChart;




    public static EventGraphFragment newInstance() {
        return new EventGraphFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_graph_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventGraphViewModel = new ViewModelProvider(this).get(EventGraphViewModel.class);
        // TODO: Use the ViewModel

        eventCategoryMap = new HashMap<>();

        LiveData<List<Event>> eventData = eventGraphViewModel.getAllEventLive();

//        eventData.observe(requireActivity(), new Observer<List<Event>>() {
//            @Override
//            public void onChanged(List<Event> events) {
//                for(int x = 0; x < events.size(); x++){
//                    String tempKey = events.get(x).getEventCategory();
//
//                    System.out.println("++++++++++++++++Onchange++++++++++++++++");
//
//                    if(eventCategoryMap.containsKey(tempKey)){
//                        eventCategoryMap.put(tempKey,eventCategoryMap.get(tempKey)+1);
//                        System.out.println("++++++++++++++++Fuck++++++++++++++++");
//                    }
//                    else {
//                        eventCategoryMap.put(tempKey,1);
//                        System.out.println("++++++++++++++++Shit++++++++++++++++");
//                    }
//                }
//
//                System.out.println( "@@@@@@@@@@@@@@@@@@@" + eventCategoryMap.size() + "@@@@@@@@@@@@@@@@@@@");
//
//                System.out.println("++++++++++++++++++++++++++++++++++++++");
//                for (String i : eventCategoryMap.keySet()) {
//                    System.out.println("key: " + i + " value: " + eventCategoryMap.get(i));
//                }
//                System.out.println("++++++++++++++++++++++++++++++++++++++");
//            }
//        });

        pieChart = getActivity().findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

//        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Food & Dining"));
        entries.add(new PieEntry(0.15f, "Medical"));
        entries.add(new PieEntry(0.10f, "Entertainment"));
        entries.add(new PieEntry(0.25f, "Electricity and Gas"));
        entries.add(new PieEntry(0.3f, "Housing"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

}