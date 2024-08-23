package com.example.test2

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ServiceLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val etName = findViewById<EditText>(R.id.LUsername)
        val etPassword = findViewById<EditText>(R.id.LPassword)
        val lBtn = findViewById<Button>(R.id.LLoginButton)
        val tvForget = findViewById<TextView>(R.id.LForgot)
        val rBtn = findViewById<Button>(R.id.LRegisterButton)

        lBtn.setOnClickListener {
            val txt_mail = etName.text.toString();
            val txt_password = etPassword.text.toString();

            loginUser(txt_mail, txt_password);
        }

        tvForget.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
            finish()
        }

        rBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun loginUser(txtMail: String, txtPassword: String) {
        val auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(txtMail, txtPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "LoginUserWithEmail:success")
                    Toast.makeText(baseContext, "Authentication successful.", Toast.LENGTH_SHORT,).show()
                    val user = auth.currentUser
                    val userId = user?.uid

                    val intent = Intent(this, ServiceHomeActivity::class.java)
                    intent.putExtra("UID", userId)
                    startActivity(intent)
                    finish()

                } else {
                    Log.w(TAG, "LoginUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }
}
