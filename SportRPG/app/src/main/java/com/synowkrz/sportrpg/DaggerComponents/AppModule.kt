package com.synowkrz.sportrpg.DaggerComponents

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.synowkrz.sportrpg.Controller.LoginControllerImpl
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.Controller.UserControllerImpl
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Database.SportRPGDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): SportRPGDatabase =
            Room.databaseBuilder(context, SportRPGDatabase::class.java, "sportRPG.db")
                    .allowMainThreadQueries()
                    .build()
    @Provides
    fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideUserTable(sportRPGDatabase: SportRPGDatabase) : UserDao = sportRPGDatabase.userDataDao()

    @Provides
    @Singleton
    fun provideUserControl(userDao: UserDao) : UserController = UserControllerImpl(userDao)

    @Provides
    fun provideLoginControl(sharedPreferences: SharedPreferences) = LoginControllerImpl(sharedPreferences)
}