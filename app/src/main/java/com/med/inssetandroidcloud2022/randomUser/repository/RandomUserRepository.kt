package com.med.inssetandroidcloud2022.randomUser.repository

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import com.med.inssetandroidcloud2022.architecture.CustomApplication
import com.med.inssetandroidcloud2022.architecture.RetrofitBuilder
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRetrofit
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomUserRepository {

    private val randomUserDao = CustomApplication.instance.mApplicationDatabase.randomUserDao()

    fun selectAllRandomUsers(): LiveData<List<RandomUserRoom>> {
        return randomUserDao.selectAll()
    }

    private suspend fun insertRandomUser(randomUser: RandomUserRoom) =
        withContext(Dispatchers.IO) {
            randomUserDao.insert(randomUser)
        }

    suspend fun deleteAllRandomUsers() = withContext(Dispatchers.IO) {
        randomUserDao.deleteAll()
    }

    suspend fun fetchData() {
        insertRandomUser(RetrofitBuilder.getRandomUser().getRandomUser().toRoom())
    }
}

private fun RandomUserRetrofit.toRoom(): RandomUserRoom {
    return RandomUserRoom(
        email = email,
        avatarUrl = avatarUrl,
        createdAt = System.currentTimeMillis()
    )
}
