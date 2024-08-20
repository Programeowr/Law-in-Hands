package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class SolvedComplaintsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solved_complaints)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val eBtn = findViewById<Button>(R.id.LSos)
        val unsolBtn = findViewById<TextView>(R.id.unsolButton)

        val userId = intent.getStringExtra("UID")?:""
        val check = intent.getIntExtra("Back", 0)

        val listView = findViewById<ListView>(R.id.solListview)
        val complaintList = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, complaintList)
        listView.adapter = adapter

        val db = FirebaseFirestore.getInstance()
        db.collection("Complaints").document(userId).collection("Complaint").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    complaintList.clear()
                    val result = task.result
                    if(result != null) {
                        for (document in result.documents) {
                            if (document != null && document.exists()) {
                                val complaint = document.toObject(Complaint::class.java)
                                if(complaint != null) {
                                    complaintList.add(complaint.title)
                                    if(complaint.title.isEmpty()){
                                        Log.d("FileComplaintActivity", "Empty")
                                    } else {
                                        Log.d("FileComplaintActivity", complaint.title)
                                    }
                                }
                            } else {
                                Log.d("ProfileActivity", "No such document")
                            }
                        }
                        Log.d("FileComplaintActivity", "Complaint list size: ${complaintList.size}")
                        adapter.clear()
                        adapter.addAll(complaintList)
                        adapter.notifyDataSetChanged()
                        Log.d("FileComplaintActivity", "Adapter notified")
                        Log.d("FileComplaintActivity", "Adapter count: ${adapter.count}")
                    }
                } else {
                    Log.d("FileComplaintActivity", "Error getting complaints", task.exception)
                }
            }



        pBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
            startActivity(intent)
            finish()
        }

        cBtn.setOnClickListener {
            val intent = Intent(this, SolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
            startActivity(intent)
            finish()
        }

        hBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
            startActivity(intent)
            finish()
        }

        sBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
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
            else if(check == 4){
                val intent = Intent(this, SettingsActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, EmergencyLoginActivity::class.java)
                intent.putExtra("UID", userId)
                startActivity(intent)
                finish()
            }
        }

        eBtn.setOnClickListener {
            val intent = Intent(this, EmergencyLoginActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
            startActivity(intent)
            finish()
        }

        unsolBtn.setOnClickListener {
            val intent = Intent(this, UnsolvedComplaintsActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 3)
            startActivity(intent)
            finish()
        }

    }
}