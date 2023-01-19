package com.med.inssetandroidcloud2022.randomUser.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.med.inssetandroidcloud2022.databinding.ActivityMainBinding
import com.med.inssetandroidcloud2022.firebase.view.FirebaseLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.firstFunctionality.setOnClickListener{
            goToFirstFunctionality()
        }
        binding.secondFunctionality.setOnClickListener{
            goToSecondFunctionality()
        }
    }

    private fun goToFirstFunctionality() {
        startActivity(Intent(this, RandomUserActivity::class.java))
    }

    private fun goToSecondFunctionality() {
        startActivity(Intent(this, FirebaseLoginActivity::class.java))
    }

}