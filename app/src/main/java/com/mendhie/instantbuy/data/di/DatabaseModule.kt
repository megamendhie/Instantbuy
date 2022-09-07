package com.mendhie.instantbuy.data.di

import android.app.Application
import androidx.room.Room
import com.mendhie.instantbuy.data.database.AppDatabase
import com.mendhie.instantbuy.data.database.InstantbuyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(app: Application, callback: AppDatabase.DbCallback)
      = Room.databaseBuilder(app, AppDatabase::class.java, "instantbuy_db")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
        //*785#

    @Singleton
    @Provides
    fun providesDoa(db: AppDatabase): InstantbuyDao = db.storeDoa()

    @Singleton
    @Provides
    fun provideAppScope() = CoroutineScope(SupervisorJob())

}