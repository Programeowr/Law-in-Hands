package com.example.test2

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class FileComplaintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_complaint)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val spinner = findViewById<Spinner>(R.id.cSpinner)
        val pBtn = findViewById<ImageView>(R.id.pProfileIcon)
        val cBtn = findViewById<ImageView>(R.id.pComplaintIcon)
        val hBtn = findViewById<ImageView>(R.id.pHomeIcon)
        val sBtn = findViewById<ImageView>(R.id.pSettingsIcon)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val eBtn = findViewById<Button>(R.id.LSos)
        val submitBtn = findViewById<Button>(R.id.cSubmit)

        val etTitle = findViewById<EditText>(R.id.cTitle)
        val etDescription = findViewById<EditText>(R.id.cDescription)
        val switch = findViewById<Switch>(R.id.cSwitch)

        val userId = intent.getStringExtra("UID")?:""
        val check = intent.getIntExtra("Back", 0)

        val options = arrayOf("Healthcare Services", "Police Services",
            "Public Safety Services", "Transportation Services", "Sanitation and Waste Management",
            "Social Services", "Postal Services", "Environmental Protection Services",
            "Education Services")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var selectedOption = ""

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                selectedOption = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(baseContext, "Nothing Selected", Toast.LENGTH_SHORT).show()
            }
        }


        pBtn.setOnClickListener {
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

        eBtn.setOnClickListener{
            val intent = Intent(this, EmergencyLoginActivity::class.java)
            intent.putExtra("UID", userId)
            intent.putExtra("Back", 1)
            startActivity(intent)
            finish()
        }

        submitBtn.setOnClickListener{
            val txttitle = etTitle.text.toString()
            val txtdescription = etDescription.text.toString()
            val isPublic = switch.isChecked
            Log.d("FileComplaintActivity", "Submit button clicked")
            saveComplaint(selectedOption, txttitle, txtdescription, isPublic, userId)

        }

    }

    private fun saveComplaint(selectedOption: String, txtTitle: String, txtDescription: String, isPublic: Boolean, userId: String){
        val complaintData = hashMapOf("Service" to selectedOption, "Title" to txtTitle,"Description" to txtDescription,"Visibility" to if (isPublic) "Public" else "Private", "Status" to "Unsolved")
        val db = FirebaseFirestore.getInstance()
        db.collection("Complaints").document(userId).collection("Complaint").add(complaintData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Complaint Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}