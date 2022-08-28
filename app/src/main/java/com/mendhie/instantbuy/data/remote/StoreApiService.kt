package com.mendhie.instantbuy.data.remote

import com.mendhie.instantbuy.data.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://fakestoreapi.com/"
const val users = "users"
const val product = "products/"
const val products = "products/category/"
const val categories = "categories"
const val login = "auth/login"
const val carts = "carts"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface StoreApiService{
    @GET(users)
    fun getUser(): Call<User>

    @POST(login)
    fun login(@Body loginDetails: LoginDetails): Call<LoginAuth>

    @POST(users)
    fun createUser(@Body user: UserFull): Call<User>

    @PUT(users)
    fun updateUser(@Body user: UserFull): Call<User>

    @GET("$product{id}")
    fun getProduct(@Path("id") id: String): Call<Product>

    @GET("$products{category}")
    fun getProducts(@Path("category") category: String): Call<List<Product>>

    @GET(carts)
    fun getCart(): Call<List<Cart>>
}

object StoreApi{
    val storeApi: StoreApiService by lazy {
        retrofit.create(StoreApiService::class.java)
    }
}