package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var buttonTrue : Button
    lateinit var buttonFalse : Button
    lateinit var question: TextView
    var isTrue =
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        buttonTrue.setOnClickListener {
            if(isTrue){
                points++
                nextQuestion()
            }
            else{
                nextQuestion()
            }

        }
        buttonFalse.setOnClickListener {

        }

    }
    private fun updateQuestion(){

    }

    private fun wireWidgets() {
        buttonFalse = findViewById(R.id.button_main_false)
        buttonTrue = findViewById(R.id.button_main_true)
        question = findViewById(R.id.textView_main_question)
    }

}