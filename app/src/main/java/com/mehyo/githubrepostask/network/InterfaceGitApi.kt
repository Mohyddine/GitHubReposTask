package com.mehyo.githubrepostask.network

import com.mehyo.githubrepostask.GitApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

//api interface using suspending function for network calls
interface InterfaceGitApi {
    //suspending function for network calls
    @GET("repositories")
    suspend fun getAPIResult(
        @Query("q") q: String?,
        @Query("sort") sort:String?,
        @Query("order") order:String?,
        @Query("page") page:Int?,
        @Query("per_page") per_page:Int?
    ): Response<GitApiResponse>


}



