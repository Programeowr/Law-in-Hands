package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class services_view_complaints : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_view_complaints)

        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)

        val userId = intent.getStringExtra("UID")?:""

        hBtn.setOnClickListener {
            val intent = Intent(this, ServiceHomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }


        sBtn.setOnClickListener {
            val intent = Intent(this, ServicesSettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            val intent = Intent(this, ServiceHomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

    }
}