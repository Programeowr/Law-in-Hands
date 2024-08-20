package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ServiceHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_home)

        val bBtn = findViewById<ImageView>(R.id.pBack)
        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val viewBtn = findViewById<Button>(R.id.hViewCom)

        val userId = intent.getStringExtra("UID")

        pBtn.setOnClickListener {
            val intent = Intent(baseContext, ServiceProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(baseContext, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }


    }
}