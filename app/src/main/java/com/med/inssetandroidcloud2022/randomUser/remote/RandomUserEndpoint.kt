package com.med.inssetandroidcloud2022.randomUser.remote

import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRetrofit
import retrofit2.http.GET

interface RandomUserEndpoint {

    @GET("random_user")
    suspend fun getRandomUser() : RandomUserRetrofit
}