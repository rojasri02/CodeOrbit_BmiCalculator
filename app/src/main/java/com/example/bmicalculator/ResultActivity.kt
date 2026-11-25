package com.example.bmicalculator

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class ResultActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bmiValue = findViewById<TextView>(R.id.bmiValue)
        val bmiCategory = findViewById<TextView>(R.id.bmiCategory)
        val healthMessage = findViewById<TextView>(R.id.healthMessage)
        val recalculateButton =
            findViewById<Button>(R.id.recalculateButton)

        val bmi = intent.getDoubleExtra("BMI", 0.0)
        val category = intent.getStringExtra("CATEGORY") ?: ""
        val age = intent.getIntExtra("AGE", 0)
        val gender = intent.getStringExtra("GENDER") ?: ""

        bmiValue.text = String.format("%.2f", bmi)
        bmiCategory.text = category

        healthMessage.text = when (category) {
            "Underweight" ->
                "Your BMI is below the normal range. Consult a healthcare professional for guidance."
            "Normal Weight" ->
                "Great! Maintain a balanced diet and regular exercise."
            "Overweight" ->
                "Consider healthy eating habits and regular physical activity."
            else ->
                "Your BMI is in the obesity range. Consult a healthcare professional for guidance."
        }

        recalculateButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
