package com.learning.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.learning.firebaseauthentication.databinding.ActivitySignUpBinding
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }

        binding.txtRedirectSignIn.setOnClickListener {

            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

    }

    private fun forDelay() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun signUpUser() {
        var email = binding.edtUserSignUpEmail.text.toString()
        var pass = binding.edtSignUpPassword.text.toString()
        var confPass = binding.edtUserLoginConfPassword.text.toString()

        if (email.isBlank()) {
            Toast.makeText(this, "Email Can't Be Blank", Toast.LENGTH_SHORT)
                .show()
            return
        } else if (pass.isBlank() || confPass.isBlank()) {
            Toast.makeText(this, "Password Can't Be Blank", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (pass != confPass) {
            Toast.makeText(this, "Password and confirm password not are same", Toast.LENGTH_SHORT)
                .show()
            return
        }


        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {

            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                Log.i("Auth", "sign up successful")

                Handler(Looper.getMainLooper()).postDelayed({
                    forDelay()
                }, 2000)

            } else {

                Toast.makeText(this, "Sign Up Failed ${auth.toString()}", Toast.LENGTH_SHORT).show()
                Log.i("Auth", "sign up failed")

            }
        }
    }
}