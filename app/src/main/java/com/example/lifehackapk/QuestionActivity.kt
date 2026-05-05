package com.example.lifehackapk

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView

    private var currentIndex = 0
    private var score = 0

    private val questions = listOf(
        Question("Putting your phone in rice fixes water damage", false),
        Question("Keyboard shortcuts improve productivity", true),
        Question("Drinking coffee fully dehydrates you", false),
        Question("Taking breaks improves focus", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackBtn = findViewById<Button>(R.id.hackButton)
        val mythBtn = findViewById<Button>(R.id.mythButton)
        val nextBtn = findViewById<Button>(R.id.nextButton)

        displayQuestion()

        hackBtn.setOnClickListener { checkAnswer(true) }
        mythBtn.setOnClickListener { checkAnswer(false) }
        nextBtn.setOnClickListener { nextQuestion() }
    }

    private fun displayQuestion() {
        questionText.text = questions[currentIndex].text
        feedbackText.text = ""
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = questions[currentIndex].answer

        if (userAnswer == correct) {
            feedbackText.text = "Correct! 🔥"
            score++
        } else {
            feedbackText.text = "Wrong! ❌"
        }
    }

    private fun nextQuestion() {
        currentIndex++

        if (currentIndex < questions.size) {
            displayQuestion()
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", questions.size)
            startActivity(intent)
            finish()
        }
    }
}