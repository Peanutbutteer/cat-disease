package com.iampeanut.cat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by satjawatpanakarn on 5/7/2017 AD.
 */

public class BasicInfomationFragment extends Fragment {
    private AppCompatSpinner spinnerGender;
    private AppCompatSpinner spinnerSpecies;
    private EditText etAge;
    private EditText etWeight;

    public static BasicInfomationFragment newInstance() {

        Bundle args = new Bundle();

        BasicInfomationFragment fragment = new BasicInfomationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_infomation, container, false);
        spinnerGender = (AppCompatSpinner) view.findViewById(R.id.spin_gender);
        spinnerSpecies = (AppCompatSpinner) view.findViewById(R.id.spin_type);
        etAge = (EditText) view.findViewById(R.id.et_age);
        etWeight = (EditText) view.findViewById(R.id.et_weight);
        initInstance();
        return view;
    }

    private void initInstance() {
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> SpeciesAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.species, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpeciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
        spinnerSpecies.setAdapter(SpeciesAdapter);
    }

    public String getGender() {
        if (spinnerGender.getSelectedItem().toString().equals("ชาย")) {
            return "M";
        }
        return "F";
    }

    public String getSpecies() {
        String species = spinnerSpecies.getSelectedItem().toString();
        if (species.equals("เปอร์เซีย")) {
            return "Persian";
        }
        if (species.equals("สกอตติชโฟลด์")) {
            return "SCOTTISH_FOLD";
        }
        return "DSH";
    }

    public String getWeight() {
        Double weight = 0.0;
        try {
            weight = Double.parseDouble(etWeight.getText().toString());
        } catch (NumberFormatException e) {

        }
        return String.valueOf(weight);
    }

    public String getAge() {
        int age = 0;
        try {
            age = Integer.parseInt(etAge.getText().toString());
        } catch (NumberFormatException e) {

        }
        return String.valueOf(age);
    }
}