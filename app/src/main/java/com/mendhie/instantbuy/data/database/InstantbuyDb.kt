package com.mendhie.instantbuy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mendhie.instantbuy.data.converters.InstantbuyConverter
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.models.User
import javax.inject.Inject

@Database(entities = [User::class, Product::class], version = 1)
@TypeConverters(InstantbuyConverter::class)
abstract class InstantbuyDb: RoomDatabase() {
    abstract fun StoreDao(): StoreDao

    class WeatherCallback @Inject constructor(): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}