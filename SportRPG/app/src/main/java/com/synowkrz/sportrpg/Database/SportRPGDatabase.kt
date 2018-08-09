package com.synowkrz.sportrpg.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Dao.TrainingDao
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.Model.Training
import com.synowkrz.sportrpg.Model.User

@Database(entities = arrayOf(User::class, Credentials::class, Training::class), version = 1)
abstract class SportRPGDatabase : RoomDatabase() {

    abstract fun userDataDao() : UserDao
    abstract fun credentialsDao() : CredentialsDao
    abstract fun trainingDao(): TrainingDao
}