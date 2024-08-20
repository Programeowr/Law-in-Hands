package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val logoutBtn = findViewById<Button>(R.id.sLogoutButton)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val appBtn = findViewById<Button>(R.id.sAppButton)
        val pwdBtn = findViewById<Button>(R.id.sChangePassword)

        val userId = intent.getStringExtra("UID")?:""
        val check = intent.getIntExtra("Back", 0)

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(baseContext, "You're logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        pBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        cBtn.setOnClickListener {
            val intent = Intent(this, SolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        hBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            if(check == 1){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
            else if(check == 2){
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
            else if(check == 3){
                val intent = Intent(this, SolvedComplaintsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, SettingsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
        }

        appBtn.setOnClickListener {
            val intent = Intent(this, AppFeedbackActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

        pwdBtn.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 4)
            startActivity(intent)
            finish()
        }

    }
}