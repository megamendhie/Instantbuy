package com.mendhie.instantbuy.data.models

data class Cart(
    val id: Int,
    val userId: Int,
    val date: String,
    var products: List<Prod>,
    val __v: Int

)

data class Prod(
    var productId: Int,
    var quantity: Int
)
