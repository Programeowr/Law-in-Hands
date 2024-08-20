package com.example.test2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val etOld = findViewById<EditText>(R.id.etOPassword)
        val etNew = findViewById<EditText>(R.id.etNewPwd)
        val etConfirm = findViewById<EditText>(R.id.etConNewPwd)
        val sBtn = findViewById<Button>(R.id.subButton)

        sBtn.setOnClickListener {
            val oldPwd = etOld.text.toString()
            val newPwd = etNew.text.toString()
            val confirmPwd = etConfirm.text.toString()

            user?.let {
                auth.signInWithEmailAndPassword(it.email!!, oldPwd)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            if(newPwd == confirmPwd) {
                                user.updatePassword(newPwd)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(this, "Error changing password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            }
                        } else {
                            Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_SHORT).show()
                            Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}