package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class AppFeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_feedback)

        val etType = findViewById<EditText>(R.id.etType)
        val subBtn = findViewById<Button>(R.id.aSubButton)
        val rBar = findViewById<RatingBar>(R.id.aRatingBar)
        val bBtn = findViewById<ImageView>(R.id.pBack)

        val userId = intent.getStringExtra("UID")?:""
        var ratingValue = 0F

        bBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }



        subBtn.setOnClickListener {
            val text = etType.text.toString()

            rBar.setOnRatingBarChangeListener { rBar, rating, fromUser ->
                ratingValue = rating
                Toast.makeText(this, "Rating: $ratingValue", Toast.LENGTH_SHORT).show()

            }
            val db = FirebaseFirestore.getInstance()
            val userFeedback = Feedback(text, ratingValue)
            db.collection("Feedback").document(userId).set(userFeedback).addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Toast.makeText(baseContext, "Values Added", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}