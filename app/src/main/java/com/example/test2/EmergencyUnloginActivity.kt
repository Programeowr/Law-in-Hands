package com.example.test2

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EmergencyUnloginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_unlogin)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val bBtn = findViewById<ImageView>(R.id.pBack)
        val polBtn = findViewById<Button>(R.id.ePoliceButton)
        val ambBtn = findViewById<Button>(R.id.eAmbulanceButton)
        val fireBtn = findViewById<Button>(R.id.eFireButton)

        bBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        polBtn.setOnClickListener {
            callService(1)
        }

        ambBtn.setOnClickListener {
            callService(2)
        }

        fireBtn.setOnClickListener {
            callService(3)
        }
    }

    private fun callService(a: Int) {
        if (a == 1) {
            val phoneNumber = "+917780796993"

            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)

        } else if (a == 2) {
            val phoneNumber = "+919642463054"

            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)
        } else {
            val phoneNumber = "+918143927635"

            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)
        }
    }
}