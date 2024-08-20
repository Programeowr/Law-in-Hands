package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val eBtn = findViewById<Button>(R.id.LSos)
        val etMail = findViewById<EditText>(R.id.fEnter)
        val rBtn = findViewById<Button>(R.id.fReset)
        val bBtn = findViewById<ImageView>(R.id.pBack)
        val blBtn = findViewById<TextView>(R.id.fBacktologin)

        eBtn.setOnClickListener {
            val intent = Intent(this, EmergencyUnloginActivity::class.java)
            startActivity(intent)
            finish()
        }

        bBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        blBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        rBtn.setOnClickListener {
            val txtmail = etMail.text.toString()
            checkEmail(txtmail)

        }

    }

    private fun checkEmail(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, "temp_password")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.user?.delete()
                    Toast.makeText(this, "Email does not exist", Toast.LENGTH_SHORT).show()
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        sendResetEmail(email)
                    } else {
                        Toast.makeText(this, "Error checking if email exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }


    private fun sendResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()
        Log.d("Forgot", "Sending reset email to $email")
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Mail sent successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error sending mail", Toast.LENGTH_SHORT).show()
                }
            }
    }
}