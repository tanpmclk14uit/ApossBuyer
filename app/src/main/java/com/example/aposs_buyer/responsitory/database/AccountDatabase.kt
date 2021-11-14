package com.example.aposs_buyer.responsitory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.database.dao.AccountDAO

@Database(entities = [Account::class], version = 2, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract val accountDao: AccountDAO

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null
        private const val DATABASE_NAME = "account.db"
        fun getInstance(context: Context): AccountDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AccountDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}