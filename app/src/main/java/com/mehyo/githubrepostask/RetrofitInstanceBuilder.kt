package com.mehyo.githubrepostask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceBuilder {
    const val BASE_URL:String="https://api.github.com/search/"

    //retrofit singleton: Instance of the Retrofit to always call the exact same Instance
    val retrofitBuilder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    //api Interface singleton: Instance of the InterFaceApi to always call the exact same Instance
    val apiService: InterfaceGitApi by lazy{
        retrofitBuilder
            .build()
            .create(InterfaceGitApi::class.java)
    }
}