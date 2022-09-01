package com.mendhie.instantbuy.data.models

data class Products(
    val products: List<Product>
)

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: rating
)

data class rating(
    val rate: Double,
    val count: Int
)

