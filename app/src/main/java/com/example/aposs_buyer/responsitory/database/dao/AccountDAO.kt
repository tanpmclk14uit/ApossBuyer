package com.example.aposs_buyer.responsitory.database.dao

import androidx.room.*
import com.example.aposs_buyer.model.entity.Account

@Dao
interface AccountDAO {

    @Insert
    fun insertAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)

    @Query("UPDATE account SET accessToken = :accessToken")
    fun updateAccessToken(accessToken: String)

    @Query("SELECT * FROM account Limit 1")
    fun getAccount(): Account?
}