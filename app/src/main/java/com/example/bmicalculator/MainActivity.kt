
package com.example.bmicalculator

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightInput = findViewById<EditText>(R.id.heightInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {

            val heightText = heightInput.text.toString().trim()
            val weightText = weightInput.text.toString().trim()
            val ageText = ageInput.text.toString().trim()

            if (heightText.isEmpty() ||
                weightText.isEmpty() ||
                ageText.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill all the fields!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val height = heightText.toDoubleOrNull()
            val weight = weightText.toDoubleOrNull()
            val age = ageText.toIntOrNull()

            if (height == null || weight == null || age == null ||
                !height.isFinite() || !weight.isFinite() ||
                height <= 0 || weight <= 0 ||
                age !in 1..120 || height > 300 || weight > 500) {

                Toast.makeText(
                    this,
                    "Please enter valid values!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val heightInMetres = height / 100.0
            val bmi = weight / (heightInMetres * heightInMetres)

            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25.0 -> "Normal Weight"
                bmi < 30.0 -> "Overweight"
                else -> "Obese"
            }

            val gender = if (
                genderGroup.checkedRadioButtonId == R.id.maleRadio
            ) "Male" else "Female"

            val intent = Intent(this, ResultActivity::class.java)

            intent.putExtra("BMI", bmi)
            intent.putExtra("CATEGORY", category)
            intent.putExtra("AGE", age)
            intent.putExtra("GENDER", gender)

            startActivity(intent)
        }
    }
}
