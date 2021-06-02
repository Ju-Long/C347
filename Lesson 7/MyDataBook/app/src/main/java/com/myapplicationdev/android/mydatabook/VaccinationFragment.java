package com.myapplicationdev.android.mydatabook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaccinationFragment extends Fragment {

    TextView tvVacc;
    Button btnVaccEdit;
    FloatingActionButton btnVaccSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccination, container, false);
        tvVacc = view.findViewById(R.id.tvVacc);
        btnVaccEdit = view.findViewById(R.id.btnVaccEdit);
        btnVaccSearch = view.findViewById(R.id.btnVaccSearch);
        return view;
    }
}