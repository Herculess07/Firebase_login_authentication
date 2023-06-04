package com.learning.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.firebaseauthentication.databinding.ActivityWelcomeBinding
import java.util.zip.Inflater

class Welcome : AppCompatActivity() {
    private lateinit var binding3 : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding3.root)
    }
}