package com.myapplicationdev.android.problemstatement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.Random;

public class Frag2 extends Fragment {

    LinearLayout layout;
    Button btnFrag2;
    String[] colors = {"#e9c46a", "#f4a261", "#ffe8d6", "#ddbea9"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        layout = view.findViewById(R.id.Frag2);
        btnFrag2 = view.findViewById(R.id.btnFrag2);
        Random r = new Random();
        layout.setBackgroundColor(Color.parseColor(colors[r.nextInt(colors.length)]));
        btnFrag2.setOnClickListener(v -> {
            int num = r.nextInt(colors.length);
            layout.setBackgroundColor(Color.parseColor(colors[num]));
        });
        return view;
    }
}