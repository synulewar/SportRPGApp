package com.synowkrz.sportrpg.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.synowkrz.sportrpg.Dao.UserDataDao
import com.synowkrz.sportrpg.Model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class SportRPGDatabase : RoomDatabase() {

    abstract fun userDataDao() : UserDataDao

    companion object {
        private var mInstance: SportRPGDatabase? = null

        fun getInstance(context: Context): SportRPGDatabase? {
            if (mInstance == null) {
                synchronized(SportRPGDatabase::class) {
                    mInstance = Room.databaseBuilder(context.applicationContext, SportRPGDatabase::class.java, "sportRPG.db")
                            .build()
                }
            }
            return mInstance
        }

        fun destroyInstance() {
            mInstance = null
        }

    }

}