package com.example.prdlycomposeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class User (
    val id: Int?,
    val username: String,
    val password: String?,
)

data class Mood (
    val id: Int?,
    val userId: Int,
    val mood: String,
    val date: String
)

data class Token (
    val token: String
)

data class Friend (
    val id: Int?,
    val userId: Int,
    val friendId: Int
)

interface ServerApi {
    companion object {
        const val BASE_URL = "http://192.168.0.165:3000/api/"
    }

    // user manipulation
    @POST("session/login")
    fun login(@Body user: User): Call<Token>

    @POST("users")
    fun register(@Body user: User): Call<Int>

    // mood
    @GET("moods")
    fun getMoods(@Header("Authorization") auth: String): Call<MutableList<Mood>>

    @POST("moods")
    fun addMood(@Body mood: Mood): Call<Int>

    // friends
    @GET("friends")
    fun getFriends(): Call<MutableList<Friend>>

    @POST("friends")
    fun addFriend(@Body friend: Friend): Call<Int>
}