package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.Credentials


@Dao
interface CredentialsDao {
    @Query("SELECT * from credentialTable")
    fun getAllCredentials() : List<Credentials>

    @Query("SELECT * from credentialTable where email = :email")
    fun getCredentials(email: String) : Credentials?

    @Insert(onConflict = REPLACE)
    fun insertCredentials(credentials: Credentials)
}