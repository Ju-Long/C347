package com.myapplicationdev.android.mydatabook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaccinationFragment extends Fragment {

    TextView tvVacc;
    Button btnVaccEdit;
    FloatingActionButton btnVaccSearch;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

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

        btnVaccEdit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Edit Vacc");
            builder.setView(inflater.inflate(R.layout.dialog_edit, null))
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Dialog d = (Dialog) dialog;
                            EditText etDialog = d.findViewById(R.id.etDialog);
                            tvVacc.setText(etDialog.getText().toString());
                            dialog.dismiss();
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("dialog", "onClick: cancel");
                    dialog.cancel();
                }
            });
            builder.create().show();
        });

        btnVaccSearch.setOnClickListener(v -> {
            View main_view = inflater.inflate(R.layout.activity_main, null);
            drawerLayout = main_view.findViewById(R.id.drawerLayout);
            toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.addDrawerListener(toggle);
        });
        return view;
    }
}