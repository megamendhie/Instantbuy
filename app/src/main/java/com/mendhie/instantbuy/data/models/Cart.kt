package com.mendhie.instantbuy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey
    var productId: Int,
    val userId: Int,
    val date: String,
    var quantity: Int
)