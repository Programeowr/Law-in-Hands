package com.example.test2

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    // private var database: DatabaseReference = FirebaseDatabase.getInstance("https://test2-819dc-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val etName = findViewById<EditText>(R.id.RFirstName)
        val etPhone = findViewById<EditText>(R.id.RMobile)
        val etEmail = findViewById<EditText>(R.id.REmail)
        val etPassword = findViewById<EditText>(R.id.RPassword)
        val etConfirm = findViewById<EditText>(R.id.RConfirm)
        val etGender = findViewById<EditText>(R.id.RGender)
        val rBtn = findViewById<Button>(R.id.RRegisterButtun)
        val bBtn = findViewById<ImageView>(R.id.pBack)

        rBtn.setOnClickListener {
            val txtmail = etEmail.text.toString()
            val txtpassword = etPassword.text.toString()
            val txtconfirm = etConfirm.text.toString()
            val txtname = etName.text.toString()
            val txtphone = etPhone.text.toString()
            val txtgender = etGender.text.toString()

            if(txtmail == "" || txtpassword == "") {
                Toast.makeText(this, "Empty Credentials", Toast.LENGTH_SHORT).show()

            } else {

                if(txtpassword != txtconfirm){
                    Toast.makeText(this, "Password not same", Toast.LENGTH_SHORT).show()
                }
                else {
                    registerUser(txtmail, txtpassword, txtname, txtphone, txtconfirm, txtgender)
                }
            }

        }

        bBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun registerUser(txtMail: String, txtPassword: String, txtName: String, txtPhone: String, txtConfirm: String, txtGender: String) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        auth.createUserWithEmailAndPassword(txtMail, txtPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Authentication successful.", Toast.LENGTH_SHORT,).show()
                    val user = auth.currentUser

                    val userId = user?.uid ?: ""
                    val userInfo = Information(txtMail, txtName, txtPhone, txtGender)

                    db.collection("Users").document(userId).set(userInfo).addOnCompleteListener(this) { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(baseContext, "Values Added", Toast.LENGTH_SHORT)
                        }

                    }

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("UID", userId)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }

    }
}