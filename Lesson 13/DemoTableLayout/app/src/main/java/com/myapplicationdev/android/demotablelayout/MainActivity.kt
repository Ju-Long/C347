package com.myapplicationdev.android.demotablelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun btnOnClick(view: View) {
        var msg = ""
        val btnSelected = view as Button
        msg = when (btnSelected.id) {
            button1.id -> "button 1 is clicked"
            button2.id -> "button 2 is clicked"
            button3.id -> "button 3 is clicked"
            button4.id -> "button 4 is clicked"
            button5.id -> "button 5 is clicked"
            else -> ""
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}