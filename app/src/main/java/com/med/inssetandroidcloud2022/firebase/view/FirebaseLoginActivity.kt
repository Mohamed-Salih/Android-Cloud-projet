package com.med.inssetandroidcloud2022.firebase.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.med.inssetandroidcloud2022.R
import com.med.inssetandroidcloud2022.databinding.ActivityFirebaseLoginBinding
import com.med.inssetandroidcloud2022.firebase.viewModel.FirebaseAuthViewModel

class FirebaseLoginActivity : AppCompatActivity() {

    private lateinit var mViewModel: FirebaseAuthViewModel
    private lateinit var binding: ActivityFirebaseLoginBinding

    private var mObserverUser = Observer<FirebaseUser> {
        updateUser(it)
    }

    private var mObserverError = Observer<Int> {
        updateError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]
        binding.firebaseButtonRegister.setOnClickListener { register() }
        binding.firebaseButtonLogin.setOnClickListener { login() }
        binding.firebaseButtonDisconnect.setOnClickListener { disconnect() }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mCurrentUser.observe(this, mObserverUser)
        mViewModel.mErrorProcess.observe(this, mObserverError)
    }

    override fun onStop() {
        mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)
        super.onStop()
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true

        if (TextUtils.isEmpty(binding.firebaseUserEmail.text.toString()) || TextUtils.isEmpty(binding.firebaseUserPassword.text.toString())) {
            isValid = false
        }
        binding.firebaseError.text = "empty field"
        return isValid
    }

    private fun login() {
        if (checkConformityFields()) {
            mViewModel.loginUser(binding.firebaseUserEmail.text.toString(), binding.firebaseUserPassword.text.toString())
        }
    }

    private fun register() {
        Toast.makeText(this@FirebaseLoginActivity, binding.firebaseUserEmail.text.toString(), Toast.LENGTH_LONG).show()
        if (checkConformityFields()) {
            mViewModel.registerNewUser(binding.firebaseUserEmail.text.toString(), binding.firebaseUserPassword.text.toString())
        }
    }

    private fun disconnect() {
        Toast.makeText(this@FirebaseLoginActivity, binding.firebaseUserEmail.text.toString(), Toast.LENGTH_LONG).show()
        mViewModel.disconnectUser()
    }

    private fun updateUser(user : FirebaseUser) {
        user.let {
            binding.firebaseLog.text = "${user.uid}-${user.email}"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateError(code : Int) {
        when(code) {
            5 -> {
                binding.firebaseError.text = "disconnected"
                binding.firebaseLog.text = "none"
            }
            9 -> binding.firebaseError.text = "current user null"
            10 -> binding.firebaseError.text = "Error when creating"
            11 -> binding.firebaseError.text = "Error when login"
            else -> binding.firebaseError.text = "All is good"
        }
    }
}