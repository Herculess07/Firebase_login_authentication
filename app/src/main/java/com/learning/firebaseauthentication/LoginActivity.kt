package com.learning.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.learning.firebaseauthentication.databinding.ActivityLoginBinding
import com.learning.firebaseauthentication.databinding.ActivitySignUpBinding
import kotlinx.coroutines.delay

class LoginActivity : AppCompatActivity() {
    private lateinit var binding2: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        auth = Firebase.auth

        checkLogInStatus()

        binding2.btnLogIn.setOnClickListener {
            loginUser()
        }

        binding2.txtRedirectSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkLogInStatus() {
        if (auth.currentUser != null) {
            startActivity(Intent(this, Welcome::class.java))
            finish()
        }
    }


    private fun loginUser() {
        var email = binding2.edtUserLoginEmail.text.toString()
        var pass = binding2.edtLogInPassword.text.toString()
        

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val emailName = email.substring(0, email.indexOf("@"))

                Toast.makeText(this, "Logged In Successfully $emailName", Toast.LENGTH_SHORT).show()

                fun forDelay() {
                    val intent = Intent(this, Welcome::class.java)
                    startActivity(intent)
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    forDelay()
                }, 2000)

            } else {
                Toast.makeText(this, "Log In Failed ${auth.toString()}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}