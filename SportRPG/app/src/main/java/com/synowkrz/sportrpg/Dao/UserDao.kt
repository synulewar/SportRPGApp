package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.User


@Dao
interface UserDao {

    @Query("SELECT * from userData WHERE name = :userName")
    fun getUser(userName : String) : User

    @Query("SELECT * from userData")
    fun getAllUsers() : List<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE from userData WHERE name = :userName")
    fun deleteUser(userName: String)

    @Query("DELETE from userData")
    fun deleteAll()
}