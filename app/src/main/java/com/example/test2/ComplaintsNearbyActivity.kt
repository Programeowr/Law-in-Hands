package com.example.test2

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ComplaintsNearbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaints_nearby)

        val bBtn = findViewById<ImageView>(R.id.pBack)
        val sBtn = findViewById<Button>(R.id.cSettingsButton)
        val listview = findViewById<ListView>(R.id.listview)


    }
}