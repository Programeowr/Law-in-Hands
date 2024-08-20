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

class ServiceProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_profile)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val tvMail = findViewById<TextView>(R.id.pGmail)
        val tvMobile = findViewById<TextView>(R.id.pMobile)
        val tvService = findViewById<TextView>(R.id.pService)
        val tvName = findViewById<TextView>(R.id.pName)

        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)

        val userId = intent.getStringExtra("UID")?:""

        FirebaseFirestore.getInstance().collection("Services").document(userId).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val userData = document.toObject(Information::class.java)
                        if (userData != null) {
                            tvName.text = userData.name
                            tvMail.text = "Email ID :         ${userData.email}"
                            tvMobile.text = "Mobile :         ${userData.phone}"
                            tvService.text = "Gender :         ${userData.gender}"

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


        hBtn.setOnClickListener {
            val intent = Intent(this, ServiceHomeActivity::class.java)
            intent.putExtra("UID", userId)
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
            val intent = Intent(this, ServiceHomeActivity::class.java)
            intent.putExtra("UID", userId)
            startActivity(intent)
            finish()
        }

    }

}

