package com.myapplicationdev.android.demokotlinloop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = "MainActivity"

        button1.setOnClickListener {
            for (i in 1..5) {
                Log.d(TAG, "$i")
            }
        }

        button2.setOnClickListener {
            for (i in editText.text.toString()) {
                Log.d(TAG, "$i")
            }
        }
    }
}