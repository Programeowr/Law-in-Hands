package com.example.test2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val chatBtn = findViewById<Button>(R.id.chatButton)

        val userId = intent.getStringExtra("UID")?:""

        pBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 2)
            startActivity(intent)
            finish()
        }

        cBtn.setOnClickListener {
            val intent = Intent(this, SolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 2)
            startActivity(intent)
            finish()
        }

        hBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 2)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 2)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            startActivity(intent)
            finish()
        }

        chatBtn.setOnClickListener {
            val phoneNumber = "++919392864240"
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber}")
            val intent = Intent(Intent.ACTION_VIEW, uri)

        }

    }
}