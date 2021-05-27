package com.myapplicationdev.android.problemstatement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class Frag3 extends Fragment {

    LinearLayout layout;
    Button btnFrag3;
    String[] colors = {"#e9c46a", "#f4a261", "#ffe8d6", "#ddbea9"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        layout = view.findViewById(R.id.Frag3);
        btnFrag3 = view.findViewById(R.id.btnFrag3);
        Random r = new Random();
        layout.setBackgroundColor(Color.parseColor(colors[r.nextInt(colors.length)]));
        btnFrag3.setOnClickListener(v -> {
            int num = r.nextInt(colors.length);
            layout.setBackgroundColor(Color.parseColor(colors[num]));
        });
        return view;
    }
}