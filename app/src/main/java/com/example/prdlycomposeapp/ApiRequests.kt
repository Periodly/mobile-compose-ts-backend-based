package com.example.prdlycomposeapp

import android.util.Log
import androidx.activity.ComponentActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRequests {
    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val api: ServerApi = retrofit.create(ServerApi::class.java)

        fun login(user: User): Any {
            return api.login(user).execute().let { response ->
                if (response.isSuccessful) {
                    return response.body()!!
                } else {
                    Log.e("ApiRequests", "login failed")
                }
            }
        }

        fun getMoods(token: String): MutableList<Mood> {
            val tokenReady = "Bearer $token"
            api.getMoods(tokenReady).execute().let { response ->
                if (response.isSuccessful) {
                    return response.body()!!
                } else {
                    Log.e("ApiRequests", "getMoods failed")
                }
            }
            return mutableListOf()
        }
    }
}