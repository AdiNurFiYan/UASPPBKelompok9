package com.example.ppb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val profileName = intent.getStringExtra("PROFILE_NAME")
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        tvProfileName.text = profileName

        val btnMessages = findViewById<Button>(R.id.btnMessages)
        btnMessages.setOnClickListener {
            val intent = Intent(this, MessagesActivity::class.java)
            startActivity(intent)
        }
    }
}
