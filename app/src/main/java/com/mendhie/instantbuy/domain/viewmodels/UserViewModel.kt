package com.mendhie.instantbuy.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mendhie.instantbuy.data.models.LoginDetails
import com.mendhie.instantbuy.data.models.LoginResponse
import com.mendhie.instantbuy.data.models.UserFull
import com.mendhie.instantbuy.data.models.UserResponse
import com.mendhie.instantbuy.domain.repositories.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: StoreRepository): ViewModel() {
    val userResponse: MutableLiveData<UserResponse> = repo.userData
    val loginResponse: MutableLiveData<LoginResponse> = repo.loginData
    val user = repo.getUserFromDb()

    fun signup(userFull: UserFull){
        repo.signup(userFull)
    }

    fun login(loginDetails: LoginDetails){
        repo.login(loginDetails)
    }
}