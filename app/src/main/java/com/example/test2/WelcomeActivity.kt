package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val uBtn = findViewById<Button>(R.id.userButton)
        val sBtn = findViewById<Button>(R.id.servicesButton)

        uBtn.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(baseContext, ServiceLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}