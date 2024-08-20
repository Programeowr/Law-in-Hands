package com.example.test2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest.permission.CALL_PHONE
import android.view.WindowManager

class EmergencyLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)

        val polBtn = findViewById<Button>(R.id.ePoliceButton)
        val ambBtn = findViewById<Button>(R.id.eAmbulanceButton)
        val fireBtn = findViewById<Button>(R.id.eFireButton)

        val userId = intent.getStringExtra("UID")?:""
        val check = intent.getIntExtra("Back", 0)

        pBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 5)
            startActivity(intent)
            finish()
        }

        cBtn.setOnClickListener {
            val intent = Intent(this, SolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 5)
            startActivity(intent)
            finish()
        }

        hBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 5)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 5)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            if (check == 1) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            } else if (check == 2) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            } else if (check == 3) {
                val intent = Intent(this, SolvedComplaintsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            } else if (check == 4) {
                val intent = Intent(this, SettingsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, EmergencyLoginActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
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