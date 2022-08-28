package com.mendhie.instantbuy.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Products(
    val products: List<Product>
)

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Embedded val rating: rating
)

data class rating(
    val rate: Double,
    val count: Int
)

