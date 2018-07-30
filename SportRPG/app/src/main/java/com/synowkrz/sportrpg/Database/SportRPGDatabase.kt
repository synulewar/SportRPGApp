package com.synowkrz.sportrpg.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class SportRPGDatabase : RoomDatabase() {

    abstract fun userDataDao() : UserDao
}