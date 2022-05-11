package com.example.fit5046_ass3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit5046_ass3.databinding.ActivityMainBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventGraphFragment extends Fragment {

    private EventGraphViewModel eventGraphViewModel;

    //private HashMap<String,Integer> eventMap;

    private PieChart pieChart;

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

        //For loop list getEventCategory
        eventGraphViewModel.getAllEventLive().observe(requireActivity(), events -> {
            Map<String, List<Event>> eventCategoryMap = new HashMap<>();
            for (Event event : events) {
                String eventCategory = event.getEventCategory();
                List<Event> eventListTmp = eventCategoryMap.get(eventCategory);
                eventListTmp = eventListTmp == null ? new ArrayList<>() : eventListTmp;
                eventListTmp.add(event);
                eventCategoryMap.put(eventCategory, eventListTmp);
            }
            loadPieChartData(eventCategoryMap);
        });

        pieChart = getActivity().findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData(new HashMap<>());
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Events Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

//        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setEnabled(true);
    }

    private void loadPieChartData(Map<String, List<Event>> eventMap) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, List<Event>> entry : eventMap.entrySet()) {
            entries.add(new PieEntry(entry.getValue().size(), entry.getKey()));
        }
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Events Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }}
//    public class MainActivity extends AppCompatActivity {
//        private ActivityMainBinding binding;
//        final String CHART_URL = "";
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            ActivityMainBinding binding =
//                    ActivityMainBinding.inflate(getLayoutInflater());
//            View view = binding.getRoot();
//            setContentView(view);
//            List<BarEntry> barEntries = new ArrayList<>();
//            barEntries.add(new BarEntry(0, 6766));
//            barEntries.add(new BarEntry(1, 4444));
//            barEntries.add(new BarEntry(2, 2222));
//            barEntries.add(new BarEntry(3, 5555));
//            barEntries.add(new BarEntry(4, 1111));
//            barEntries.add(new BarEntry(5, 3656));
//            barEntries.add(new BarEntry(6, 3435));
//            BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
//            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//            List<String> xAxisValues = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tues",
//                    "Wed", "Thurs", "Fri","Sat"));
//
//    }
//    }
//
//}