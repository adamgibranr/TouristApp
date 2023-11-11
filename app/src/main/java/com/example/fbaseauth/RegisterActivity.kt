package com.example.fbaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val logintext: TextView = findViewById(R.id.textView_login_now)

        logintext.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.btn_register)

        registerButton.setOnClickListener {
            performSignUp()
        }

        //lets get email and password from thee user
    }

    private fun performSignUp(){
        val email = findViewById<EditText>(R.id.editText_email_register)
        val passwd = findViewById<EditText>(R.id.editText_password_register)

        if (email.text.isEmpty() || passwd.text.isEmpty()){
            Toast.makeText(this, "email & password kosong", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = passwd.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext,
                        "Authentication success.",
                        Toast.LENGTH_SHORT,
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error",Toast.LENGTH_SHORT)
                    .show()
            }
    }
}