package com.mendhie.instantbuy.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mendhie.instantbuy.data.models.Cart
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.models.User

@Dao
interface InstantbuyDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteUser()

    @Insert(onConflict = REPLACE)
    fun insertCarts(carts: List<Cart>)

    @Query("DELETE FROM cart_table")
    fun deleteAllCarts()

    @Query("DELETE FROM cart_table WHERE productId = :id")
    fun deleteCart(id: String)

    @Insert(onConflict = REPLACE)
    fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM product_table WHERE category = :category")
    fun getProducts(category: String): LiveData<List<Product>>

    @Query("DELETE FROM product_table" )
    fun deleteProducts()

}
