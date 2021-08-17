package com.myapplicationdev.android.demokotlinbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetAge.setOnClickListener {
            val userDOB = etDOB.text.toString()
            if (userDOB.isNotEmpty()) {
                val year = Calendar.getInstance().get(Calendar.YEAR)
                val age = year - userDOB.toInt()

                var output = ""
//                if (age < 18)
//                    output = "Underage"
//                else if (age in 18..65)
//                    output = "Young people"
//                else if (age in 66..79)
//                    output = "Middle-aged"
//                else if (age in 80..99)
//                    output = "Elderly"
//                else
//                    output = "Long-lived elderly"

                output = when (age) {
                    in 0..17 -> "Underage"
                    in 18..65 -> "Young people"
                    in 66..79 -> "Middle-aged"
                    in 80..99 -> "Elderly"
                    else -> {
                        "Long-lived elderly"
                    }
                }
                tvShowAge.text = "Your age is $age \n$output"
            } else {
                Toast.makeText(this, "Please enter your birth year", Toast.LENGTH_SHORT).show()
            }
        }
    }
}