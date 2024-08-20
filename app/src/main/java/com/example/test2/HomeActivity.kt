package com.example.test2

import android.content.Intent
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val eBtn = findViewById<Button>(R.id.LSos)
        val q1Btn = findViewById<TextView>(R.id.HQueries)
        val q2Btn = findViewById<ImageView>(R.id.HQueriesIcon)
        val f1Btn = findViewById<TextView>(R.id.hFile)
        val f2Btn = findViewById<ImageView>(R.id.hFileIcon)

        val userId = intent.getStringExtra("UID")

        pBtn.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        cBtn.setOnClickListener {
            val intent = Intent(this, SolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        hBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        eBtn.setOnClickListener {
            val intent = Intent(this, EmergencyLoginActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        q1Btn.setOnClickListener {
            val intent = Intent(this, QueryActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        q2Btn.setOnClickListener {
            val intent = Intent(this, QueryActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        f1Btn.setOnClickListener {
            val intent = Intent(this, FileComplaintActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            Log.d("Complaint", "Clicked Button")
            finish()
        }

        f2Btn.setOnClickListener {
            val intent = Intent(this, FileComplaintActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            Log.d("Complaint", "Clicked Button")
            finish()
        }

    }
}