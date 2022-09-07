package com.mendhie.instantbuy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mendhie.instantbuy.data.models.Cart
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.models.User
import javax.inject.Inject

@Database(entities = [User::class, Product::class, Cart::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun storeDoa(): InstantbuyDao

    class DbCallback @Inject constructor(): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}