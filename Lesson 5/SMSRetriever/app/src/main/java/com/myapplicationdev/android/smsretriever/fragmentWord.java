package com.myapplicationdev.android.smsretriever;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fragmentWord extends Fragment {

    EditText etWord;
    Button btnWordGet;
    TextView tvWordResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        etWord = view.findViewById(R.id.etWord);
        btnWordGet = view.findViewById(R.id.btnWordGet);
        tvWordResult = view.findViewById(R.id.tvWordResult);

        Context applicationContext = MainActivity.getContextOfApplication();

        btnWordGet.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://sms");
            String[] reqCols = new String[]{"date", "address", "body", "type"};
            ContentResolver cr = applicationContext.getContentResolver();

            if (!etWord.getText().toString().trim().isEmpty()) {
                String filter = "body LIKE ?";
                String[] filterargs = {"%"+etWord.getText().toString().trim()+"%"};

                Cursor cursor = cr.query(uri, reqCols, filter, filterargs, null);
                String smsBody = "";
                if (cursor.moveToFirst()) {
                    do {
                        long dateInMillis = cursor.getLong(0);
                        String date = (String) DateFormat.format("dd MM yyyy h:mm:ss aa", dateInMillis);
                        String address = cursor.getString(1);
                        String body = cursor.getString(2);
                        String type = cursor.getString(3);
                        if (type.equalsIgnoreCase("1"))
                            type = "Inbox:";
                        else
                            type = "Sent:";
                        smsBody += type + " " + address + "\n at" + date + "\n\"" + body + "\"\n\n";
                    } while (cursor.moveToNext());
                }
                tvWordResult.setText(smsBody);
            } else
                return;
        });
        return view;
    }
}