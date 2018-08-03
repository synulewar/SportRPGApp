package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.User


@Dao
interface UserDao {

    @Query("SELECT * from userData WHERE email = :email")
    fun getUser(email : String) : User

    @Query("SELECT * from userData")
    fun getAllUsers() : List<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE from userData WHERE name = :email")
    fun deleteUser(email: String)

    @Query("DELETE from userData")
    fun deleteAll()
}