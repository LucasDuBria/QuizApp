package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
        companion object{
            val TAG = "MainActivity"
        }
    lateinit var buttonFalse : Button
    lateinit var buttonTrue : Button
    private lateinit var quiz : Quiz
    lateinit var textView: TextView
    lateinit var points: TextView
    lateinit var end: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonString")
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, type)
        Log.d(TAG, "onCreate: $questions")
        quiz = Quiz(questions)
        val scoreText = getString(R.string.main_score)
        points.text = "$scoreText ${quiz.getScore()}"
        textView.text = "${quiz.getQuestion()}"
        end.visibility = View.GONE
        buttonTrue.setOnClickListener {
            if(quiz.checkAnswer()){
                if(quiz.getAnswer()){
                    quiz.updateScore()
                    quiz.nextQuestion()
                    textView.text = quiz.getQuestion()
                    points.text = "$scoreText ${quiz.getScore()}"
                }
                else{
                    quiz.nextQuestion()
                    textView.text = quiz.getQuestion()
                    points.text = "$scoreText ${quiz.getScore()}"
                }
            }
            else{
                end.visibility = View.VISIBLE
                buttonTrue.visibility = View.GONE
                buttonFalse.visibility = View.GONE
                textView.visibility = View.GONE
            }
        }
        buttonFalse.setOnClickListener {
            if(quiz.checkAnswer()){
                if(!quiz.getAnswer()){
                    quiz.updateScore()
                    quiz.nextQuestion()
                    textView.text = "${quiz.getQuestion()}"
                    points.text = "$scoreText ${quiz.getScore()}"
                }
                else{
                    quiz.nextQuestion()
                    textView.text = "${quiz.getQuestion()}"
                    points.text = "$scoreText ${quiz.getScore()}"
                }
            }
            else{
                end.visibility = View.VISIBLE
                buttonTrue.visibility = View.GONE
                buttonFalse.visibility = View.GONE
                textView.visibility = View.GONE

            }

        }

    }
    private fun wireWidgets(){
        buttonFalse = findViewById(R.id.button_main_false)
        buttonTrue = findViewById(R.id.button_main_true)
        textView = findViewById(R.id.textView_main_question)
        points = findViewById(R.id.main_textView_score)
        end = findViewById(R.id.main_textView_end)
    }
}