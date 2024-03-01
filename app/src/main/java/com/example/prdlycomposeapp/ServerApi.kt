package com.example.prdlycomposeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerApi {
    companion object {
        const val BASE_URL = "http://localhost:3000/api/"
    }



//    // products
//    @GET("products")
//    fun getProducts(): Call<MutableList<Product>>
//
//    @GET("products/{id}")
//    fun getProduct(@Path("id") id: Int): Call<Product>
//
//    @POST("products")
//    fun addProduct(@Body product: Product): Call<Int>
//
//    @PUT("products/{id}")
//    fun updateProduct(@Path("id") id: Int, @Body product: Product): Call<Product>
//
//    @PATCH("products/{id}")
//    fun patchProduct(@Path("id") id: Int, @Body product: Product): Call<Product>
//
//    @DELETE("products/{id}")
//    fun deleteProduct(@Path("id") id: Int): Call<Int>
//
//    // users
//    @GET("users")
//    fun getUsers(): Call<MutableList<User>>
//
//    @GET("users/{id}")
//    fun getUser(@Path("id") id: Int): Call<User>
//
//    @POST("users")
//    fun addUser(@Body user: User): Call<Int>
//
//    @PUT("users/{id}")
//    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>
//
//    @PATCH("users/{id}")
//    fun patchUser(@Path("id") id: Int, @Body user: User): Call<User>
//
//    @DELETE("users/{id}")
//    fun deleteUser(@Path("id") id: Int): Call<Int>
//
//    // orders
//    @GET("orders")
//    fun getOrders(): Call<MutableList<Order>>
//
//    @GET("orders/{id}")
//    fun getOrderById(@Path("id") id: Int): Call<Order>
//
//    @POST("orders")
//    fun addOrder(@Body order: Order): Call<Int>
//
//    @DELETE("orders/{id}")
//    fun deleteOrder(@Path("id") id: Int): Call<Int>
}