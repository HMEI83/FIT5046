package com.example.fit5046_ass3;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test extends Fragment {

    private Button test_button;
    private JSONArray jarray;

    private TestViewModel mViewModel;

    public static Test newInstance() {
        return new Test();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.test_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(TestViewModel.class);
        // TODO: Use the ViewModel
        test_button = requireActivity().findViewById(R.id.test);

        GetEvent getEvent = new GetEvent();


        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String des = "";

                try {
                    getEvent.getEvents();
                    jarray = getEvent.getJarray();
                    JSONObject object = jarray.getJSONObject(1);
                    des = object.getString("address");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(),des, Toast.LENGTH_SHORT).show();
            }
        });
    }

}