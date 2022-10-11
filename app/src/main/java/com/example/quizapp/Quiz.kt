package com.example.quizapp

import android.widget.Button
import android.widget.TextView

class Quiz(val questions: List<Question>) {
    var points = 0
    var currentQuestion = 0

        // variables to track score, current question

        // functions to check if there's another question,
        // give the next question, check the answer
        // give the final score, reset the quiz?, shuffle questions?
    fun getAnswer() : Boolean{
        return questions.get(currentQuestion).answer
    }
    fun getQuestion() : String{
        return questions.get(currentQuestion).question
    }
    fun getScore(): Int {
        return points
    }
    fun updateScore(){
        points++
    }
    fun nextQuestion(){
        currentQuestion++
    }
    fun checkAnswer() : Boolean{
        return questions.size-1 > currentQuestion
    }
}