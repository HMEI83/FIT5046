package com.example.fit5046_ass3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class AddEventFragment extends Fragment {
    private Button submitBtn;
    private EditText addEventName, addEventTime;
    private EventViewModel eventViewModel;

    public AddEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        submitBtn = requireActivity().findViewById(R.id.submitBtn);
        addEventName = requireActivity().findViewById(R.id.addEventName);
        addEventTime = requireActivity().findViewById(R.id.addEventTime);
        submitBtn.setEnabled(false);
        addEventName.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(addEventName,0);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String eventName = addEventName.getText().toString().trim();
                String eventTime = addEventTime.getText().toString().trim();
                submitBtn.setEnabled(!eventName.isEmpty() && !eventTime.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        };

        addEventName.addTextChangedListener(textWatcher);
        addEventTime.addTextChangedListener(textWatcher);
/*
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = addEventName.getText().toString().trim();
                String eventTime = addEventTime.getText().toString().trim();
                Event event = new Event(eventName,eventTime);
                eventViewModel.insertEvent(event);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
                InputMethodManager inputMethodManager = (InputMethodManager) requireActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
            }
        });*/
    }
}