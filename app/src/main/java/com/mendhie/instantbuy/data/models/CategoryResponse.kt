package com.mendhie.instantbuy.data.models

data class CategoryResponse(
    val code: Int,
    val message: String,
    val categories: List<Category>
)
