package com.mendhie.instantbuy.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.models.User

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()

    @Query("SELECT * FROM product_table")
    fun getWeatherForecast(): LiveData<List<Product>>

    @Query("DELETE FROM product_table")
    suspend fun deleteProducts()
}