package com.myapplicationdev.android.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var function = 0
    var num = 0.0
    var temp_num = 0.0
    var active = false
    var dot_active = false
    var result_active = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun funcClicked(view: View) {
        val btnClicked = view as Button
        val functionClicked = btnClicked.id
        when (functionClicked) {
            toPositive.id -> {
                if (active && temp_num < 0) {
                    temp_num *= -1
                    resultNumber.text = "$temp_num"
                } else if (!active && num < 0) {
                    num *= -1
                    resultNumber.text = "$num"
                }
            }
            percentage.id -> {
                if (active) {
                    temp_num /= 100
                    resultNumber.text = "$temp_num"
                } else {
                    num /= 100
                    resultNumber.text = "$num"
                }
            }
            result.id -> {
                when (function) {
                    plus.id -> num += temp_num
                    minus.id -> num -= temp_num
                    multiply.id -> num *= temp_num
                    divide.id -> num /= temp_num
                }
                resultNumber.text = "$num"
                temp_num = 0.0
                active = false
                dot_active = false
                function = 0
                result_active = true
            }
            reset.id -> {
                num = 0.0
                temp_num = 0.0
                active = false
                dot_active = false
                function = 0
                resultNumber.text = "$num";
            }
            else -> {
                active = true
                dot_active = false
                function = functionClicked
                btnClicked.setBackgroundColor(ContextCompat.getColor(this, R.color.orange_selected))
            }
        }
    }

    fun numClicked(view: View) {
        var clicked = 0
        val numButton = view as Button
        numButton.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_selected))
        clicked = when (numButton.id) {
            num_0.id -> 0
            num_1.id -> 1
            num_2.id -> 2
            num_3.id -> 3
            num_4.id -> 4
            num_5.id -> 5
            num_6.id -> 6
            num_7.id -> 7
            num_8.id -> 8
            num_9.id -> 9
            else -> 0
        }
        if (numButton.id == dot.id)
            dot_active = true

        if (result_active && function == 0) {
            result_active = false
            num = 0.0
            num += clicked
            if (dot_active)
                num /= 10
            resultNumber.text = "$num"
        } else {
            if (!active) {
                num = (num * 10) + clicked
                if (dot_active)
                    num = (num / 10)
                resultNumber.text = "$num"
            } else {
                temp_num = (temp_num * 10) + clicked
                if (dot_active)
                    temp_num = (temp_num / 10)
                resultNumber.text = "$temp_num"
            }
        }
        if (function != 0) {
            val buttonClicked = findViewById<Button>(function)
            buttonClicked.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        }
        numButton.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
    }
}