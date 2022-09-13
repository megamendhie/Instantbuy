package com.mendhie.instantbuy.domain.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mendhie.instantbuy.data.database.InstantbuyDao
import com.mendhie.instantbuy.data.models.*
import com.mendhie.instantbuy.data.remote.StoreApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StoreRepository @Inject constructor(val dao: InstantbuyDao, val scope: CoroutineScope) {
    private val TAG = "StoreRepository"
    val userData = MutableLiveData<UserResponse>()
    val loginData = MutableLiveData<LoginResponse>()
    val cartData = MutableLiveData<List<Cart>>()


    fun login(loginDetails: LoginDetails){
        StoreApi.storeApi.login(loginDetails).enqueue(object :
            Callback<LoginAuth> {
            override fun onResponse(call: Call<LoginAuth>, response: Response<LoginAuth>) {
                if(response.code()==200 && !response.body()!!.token.isEmpty()){
                    Log.i(TAG, "onResponse:200 auth- ${response.body()}")
                    loginData.value = LoginResponse(200, response.body()!!.token)
                }
                else{
                    Log.i(TAG, "onResponse:401 auth- ${response.body()}")
                    loginData.value = LoginResponse(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<LoginAuth>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                loginData.value = LoginResponse(404, t.localizedMessage!!.toString())
            }
        })
    }

    fun signup(user: UserFull){
        StoreApi.storeApi.createUser(user).enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code()==200){
                    val body = response.body()!!
                    body.name = user.name
                    scope.launch {
                        dao.insertUser(body)
                    }
                    userData.value = UserResponse(200, "OK", body)
                    Log.i(TAG, "onResponse:200 auth- ${response.body()}")
                }
                else if(response.code()==401){
                    Log.i(TAG, "onResponse:401 auth- ${response.body()}")
                    userData.value = UserResponse(401, response.body().toString(), null)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                //binding.btnLogin.isEnabled = true
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                userData.value = UserResponse(404, t.localizedMessage!!, null)
            }
        })
    }

    fun getUserFromDb(): LiveData<User> {
        return dao.getUser()
    }

    fun getCategories(): LiveData<List<Category>>{
        StoreApi.storeApi.getCategories().enqueue(object : Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                Log.i(TAG, "onResponse: ${response.body()}")
                if(response.body()!=null){
                    val categories = response.body()!!.map { name -> Category(name) }
                    saveCategoriesToDb(categories)
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        return dao.getCategories()
    }

    private fun saveCategoriesToDb(categories: List<Category>) {
        scope.launch {dao.insertCategories(*categories.toTypedArray())}
    }

    fun getProducts(category: String): LiveData<List<Product>>{
        StoreApi.storeApi.getProducts(category).enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.i(TAG, "onResponse: ${response.body()}")
                if(response.body()!=null){
                    val products: List<Product> = response.body()!!
                    saveProductsToDb(products)
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })

        return dao.getProducts(category)
    }

    private fun saveProductsToDb(products: List<Product>) {
        scope.launch {dao.insertProducts(*products.toTypedArray())}
    }

}