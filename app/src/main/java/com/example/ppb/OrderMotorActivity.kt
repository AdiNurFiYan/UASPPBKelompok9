package com.example.ppb

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderMotorActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var rgMotorTypes: RadioGroup
    private lateinit var spinnerDays: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_motor)

        databaseHelper = DatabaseHelper(this)
        rgMotorTypes = findViewById(R.id.rgMotorTypes)
        spinnerDays = findViewById(R.id.spinnerDays)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        loadMotorTypes()
        loadRentalDays()

        btnOrder.setOnClickListener {
            val selectedMotorType = findViewById<RadioButton>(rgMotorTypes.checkedRadioButtonId).text.toString()
            val selectedDay = spinnerDays.selectedItem.toString()
            sendEmail(selectedMotorType, selectedDay)
        }
    }

    @SuppressLint("Range")
    private fun loadMotorTypes() {
        val cursor: Cursor? = databaseHelper.getAllMotorTypes()
        cursor?.let {
            while (it.moveToNext()) {
                val motorType = it.getString(it.getColumnIndex("name"))
                val radioButton = RadioButton(this)
                radioButton.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                radioButton.text = motorType
                rgMotorTypes.addView(radioButton)
            }
        }
        cursor?.close()
    }

    private fun loadRentalDays() {
        val daysList = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for (i in 1..4) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            daysList.add(dateFormat.format(calendar.time))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, daysList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDays.adapter = adapter
    }

    private fun sendEmail(motorType: String, rentalDay: String) {
        val email = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("adikembar0683@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Pemesanan Motor")
            putExtra(Intent.EXTRA_TEXT, "Saya ingin memesan motor $motorType pada hari $rentalDay. Terima kasih.")
        }
        if (email.resolveActivity(packageManager) != null) {
            startActivity(email)
        }
    }
}