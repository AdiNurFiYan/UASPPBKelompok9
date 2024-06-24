package com.example.ppb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        val btnMotor = findViewById<Button>(R.id.btnMotor)
        val btnMobil = findViewById<Button>(R.id.btnMobil)

        btnMotor.setOnClickListener {
            val intent = Intent(this, OrderMotorActivity::class.java)
            startActivity(intent)
        }

        btnMobil.setOnClickListener {
            val intent = Intent(this, OrderMobilActivity::class.java)
            startActivity(intent)
        }
    }
}