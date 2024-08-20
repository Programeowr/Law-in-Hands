package com.example.test2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val tvMail = findViewById<TextView>(R.id.pGmail)
        val tvMobile = findViewById<TextView>(R.id.pMobile)
        val tvGender = findViewById<TextView>(R.id.pGender)
        val tvName = findViewById<TextView>(R.id.pName)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val eBtn = findViewById<Button>(R.id.LSos)

        val userId = intent.getStringExtra("UID") ?: ""
        val check = intent.getIntExtra("Back", 0)

        FirebaseFirestore.getInstance().collection("Users").document(userId).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val userData = document.toObject(Information::class.java)
                        if (userData != null) {
                            tvName.text = userData.name
                            tvMail.text = "Email ID :         ${userData.email}"
                            tvMobile.text = "Mobile :         ${userData.phone}"
                            tvGender.text = "Gender :         ${userData.gender}"

                        } else {
                            Log.d("ProfileActivity", "No such document")
                        }
                    } else {
                        Log.d("ProfileActivity", "get failed with ", task.exception)
                    }
                } else {
                    Log.d("ProfileActivity", "get failed with ", task.exception)
                }
            }

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

        eBtn.setOnClickListener {
            val intent = Intent(this, EmergencyLoginActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 2)
            startActivity(intent)
            finish()
        }
    }
}

