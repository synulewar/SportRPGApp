package com.synowkrz.sportrpg.DaggerComponents

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.media.browse.MediaBrowser
import android.preference.PreferenceManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.synowkrz.sportrpg.Controller.*
import com.synowkrz.sportrpg.Dao.*
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
    fun provideCredentialsTable(sportRPGDatabase: SportRPGDatabase) : CredentialsDao = sportRPGDatabase.credentialsDao()

    @Provides
    @Singleton
    fun provideTrainingTable(sportRPGDatabase: SportRPGDatabase) : TrainingDao = sportRPGDatabase.trainingDao()

    @Provides
    @Singleton
    fun provideItemTable(sportRPGDatabase: SportRPGDatabase) : ItemDao = sportRPGDatabase.itemDao()

    @Provides
    @Singleton
    fun provdiesSkillTable(sportRPGDatabase: SportRPGDatabase) : SkillDao = sportRPGDatabase.skillDao()

    @Provides
    @Singleton
    fun provideUserControl(userDao: UserDao) : UserController = UserControllerImpl(userDao)

    @Provides
    @Singleton
    fun provideCharacterPresenter(userDao: UserDao) : CharacterPresenter = CharacterPresenterImpl(userDao)

    @Provides
    fun provideLoginControl(sharedPreferences: SharedPreferences, credentialsDao: CredentialsDao) : LoginController = LoginControllerImpl(sharedPreferences, credentialsDao)

    @Provides
    fun provideNewActivityController(credentialsDao: CredentialsDao, userDao: UserDao, sharedPreferences: SharedPreferences) : NewAccountController = NewAccountControllerImpl(credentialsDao, userDao, sharedPreferences)


    @Provides
    fun locationServer(context: Context) : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun provideTrainingPresenter(trainingDao: TrainingDao) : TrainingPresenter = TrainingPresenterImpl(trainingDao)
}