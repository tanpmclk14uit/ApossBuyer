package com.example.aposs_buyer.responsitory.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aposs_buyer.model.entity.Account

@Dao
interface AccountDAO {

    @Insert
    fun insertAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)

    @Query("SELECT * FROM account")
    fun getAccount(): List<Account>
}