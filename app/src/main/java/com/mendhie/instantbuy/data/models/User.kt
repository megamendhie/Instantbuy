package com.mendhie.instantbuy.data.models
import com.google.gson.annotations.SerializedName

data class User(
    val address: Address,
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val phone: String
)

data class UserFull(
    val address: Address,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val phone: String
)

data class UserShort(
    val email: String,
    val username: String,
    val password: String,
    val name: Name
)

data class Name(
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String
)

data class Geolocation(
    val lat: String,
    val long: String
)

data class Address(
    val geolocation: Geolocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String
)