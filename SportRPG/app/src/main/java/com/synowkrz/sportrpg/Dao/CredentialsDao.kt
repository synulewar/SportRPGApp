package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.net.Credentials

@Dao
interface CredentialsDao {
    @Query("SELECT * from credentialTable")
    fun getAllCredentials()

    @Query("SELECT * from credentialTable where email = :email")
    fun getUserPassword(email: String)

    @Insert(onConflict = REPLACE)
    fun insertCredentials(credentials: Credentials)
}