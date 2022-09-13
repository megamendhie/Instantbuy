package com.mendhie.instantbuy.data.models
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val _id: String,
    @Embedded val address: Address,
    val email: String,
    val username: String,
    @Embedded var name: Name,
    val phone: String
)

data class UserFull(
    val address: AddressFull,
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
    @SerializedName("lat") val lat: String,
    @SerializedName("long") val lg: String
)

data class AddressFull(
    @Embedded val geolocation: Geolocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String = ""
)

data class Address(
    @Embedded val geolocation: Geolocation,
    val city: String,
    val street: String,
    val number: Int
)