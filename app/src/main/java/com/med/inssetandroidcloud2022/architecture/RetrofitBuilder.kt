package com.med.inssetandroidcloud2022.architecture

import com.google.gson.GsonBuilder
import com.med.inssetandroidcloud2022.randomUser.remote.RandomUserEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://random-data-api.com/api/users/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()


    fun getRandomUser(): RandomUserEndpoint = retrofit.create(RandomUserEndpoint::class.java)
}