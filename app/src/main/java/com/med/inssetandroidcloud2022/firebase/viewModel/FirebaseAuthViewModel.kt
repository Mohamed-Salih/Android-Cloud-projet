package com.med.inssetandroidcloud2022.firebase.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.med.inssetandroidcloud2022.firebase.repository.FirebaseAuthRepository

class FirebaseAuthViewModel : ViewModel() {

    private val mFirebaseAuthRepository: FirebaseAuthRepository by lazy { FirebaseAuthRepository() }
    var mCurrentUser = MutableLiveData<FirebaseUser>()
    var mErrorProcess = MutableLiveData<Int>()

    init {
        mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        mErrorProcess = mFirebaseAuthRepository.mErrorProcess
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}