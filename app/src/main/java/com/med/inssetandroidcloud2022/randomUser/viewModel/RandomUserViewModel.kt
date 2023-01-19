package com.med.inssetandroidcloud2022.randomUser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRoom
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserUi
import com.med.inssetandroidcloud2022.randomUser.repository.RandomUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomUserViewModel : ViewModel() {

    private val randomUserRepository: RandomUserRepository by lazy { RandomUserRepository() }
    var randomUserLiveData: LiveData<List<RandomUserUi>> =
        randomUserRepository.selectAllRandomUsers().map {
            it.toUi()
        }

    fun fetchNewUser() {
        viewModelScope.launch(Dispatchers.IO) {
            randomUserRepository.fetchData()
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            randomUserRepository.deleteAllRandomUsers()
        }
    }
}

private fun List<RandomUserRoom>.toUi(): List<RandomUserUi> {
    return asSequence().map {
        RandomUserUi(
            email = it.email,
            avatarUrl = it.avatarUrl
        )
    }.toList()
}