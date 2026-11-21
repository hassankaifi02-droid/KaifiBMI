package com.kaifi.bmi

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.text.InputType

class MainActivity : Activity() {
    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var result: TextView
    private lateinit var category: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 28)
            setBackgroundColor(0xFFF5F7FB.toInt())
        }
        scroll.addView(root)
        setContentView(scroll)

        val heading = TextView(this).apply {
            text = "Kaifi BMI"
            textSize = 30f
            setTextColor(0xFF172554.toInt())
            setTypeface(null, Typeface.BOLD)
        }
        root.addView(heading)

        val subtitle = TextView(this).apply {
            text = "Body Mass Index Calculator"
            textSize = 16f
            setTextColor(0xFF475569.toInt())
            setPadding(0, 6, 0, 28)
        }
        root.addView(subtitle)

        root.addView(label("Weight (kg)"))
        weightInput = field("e.g. 78", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
        root.addView(weightInput, params())

        root.addView(label("Height (cm)"))
        heightInput = field("e.g. 154", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
        root.addView(heightInput, params())

        val calculate = Button(this).apply {
            text = "Calculate BMI"
            textSize = 17f
            isAllCaps = false
            setOnClickListener { calculateBmi() }
        }
        root.addView(calculate, params(top = 18))

        result = TextView(this).apply {
            text = "Your BMI will appear here"
            textSize = 25f
            setTypeface(null, Typeface.BOLD)
            setTextColor(0xFF0F172A.toInt())
            gravity = Gravity.CENTER
            setPadding(12, 28, 12, 8)
        }
        root.addView(result)

        category = TextView(this).apply {
            text = "Enter your weight and height to begin."
            textSize = 17f
            gravity = Gravity.CENTER
            setTextColor(0xFF475569.toInt())
            setPadding(12, 4, 12, 18)
        }
        root.addView(category)

        val note = TextView(this).apply {
            text = "Adult BMI guide\nUnderweight: below 18.5\nHealthy range: 18.5–24.9\nOverweight: 25.0–29.9\nObesity: 30.0 or above\n\nBMI is a screening measure, not a diagnosis. Adult ranges may not apply to children, pregnancy, or some athletes."
            textSize = 14f
            setTextColor(0xFF64748B.toInt())
            setPadding(4, 20, 4, 8)
        }
        root.addView(note)
    }

    private fun label(value: String) = TextView(this).apply {
        text = value
        textSize = 16f
        setTypeface(null, Typeface.BOLD)
        setTextColor(0xFF334155.toInt())
        setPadding(0, 14, 0, 8)
    }

    private fun field(hintText: String, type: Int) = EditText(this).apply {
        hint = hintText
        inputType = type
        textSize = 18f
        singleLine = true
        setPadding(16, 12, 16, 12)
        setBackgroundColor(0xFFFFFFFF.toInt())
    }

    private fun params(top: Int = 8) = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply { topMargin = top }

    private fun calculateBmi() {
        val weight = weightInput.text.toString().toDoubleOrNull()
        val heightCm = heightInput.text.toString().toDoubleOrNull()

        if (weight == null || heightCm == null || weight <= 0 || heightCm <= 0) {
            result.text = "Check your entries"
            category.text = "Please enter valid weight and height values."
            return
        }
        if (heightCm > 300 || weight > 500) {
            result.text = "Check your entries"
            category.text = "Please check the units: kg and cm."
            return
        }

        val heightM = heightCm / 100.0
        val bmi = weight / (heightM * heightM)
        val label = when {
            bmi < 18.5 -> "Underweight"
            bmi < 25.0 -> "Healthy range"
            bmi < 30.0 -> "Overweight"
            else -> "Obesity"
        }
        result.text = String.format(java.util.Locale.US, "BMI: %.1f", bmi)
        category.text = label
    }
}
