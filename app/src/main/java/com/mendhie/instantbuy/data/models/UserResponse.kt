package com.mendhie.instantbuy.data.models

data class UserResponse(
    val code: Int,
    val message: String,
    val user: User?
)
