package com.synowkrz.sportrpg.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.synowkrz.sportrpg.Dao.*
import com.synowkrz.sportrpg.Model.*

@Database(entities = arrayOf(User::class, Credentials::class, Training::class, Item::class, Skill::class), version = 1)
abstract class SportRPGDatabase : RoomDatabase() {

    abstract fun userDataDao() : UserDao
    abstract fun credentialsDao() : CredentialsDao
    abstract fun trainingDao(): TrainingDao
    abstract fun itemDao() : ItemDao
    abstract fun skillDao(): SkillDao
}