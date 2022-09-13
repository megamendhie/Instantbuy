package com.mendhie.instantbuy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey
    val name: String
    )
