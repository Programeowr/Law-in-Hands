package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ServicesSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_settings)

        val fbBtn = findViewById<Button>(R.id.sAppButton)
        val chatBtn = findViewById<Button>(R.id.sChatButton)
        val changepwdBtn = findViewById<Button>(R.id.sChangePassword)
        val logoutBtn = findViewById<Button>(R.id.sLogoutButton)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)

        val userId = intent.getStringExtra("UID")?:""
        val check = intent.getIntExtra("Back", 0)

        hBtn.setOnClickListener {
            val intent = Intent(this, ServiceHomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, ServicesSettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            if(check == 1){
                val intent = Intent(this, ServiceHomeActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }

            else{
                val intent = Intent(this, ServicesSettingsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
        }

    }
}