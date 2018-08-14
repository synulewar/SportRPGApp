package com.synowkrz.sportrpg.DaggerComponents

import android.app.Activity
import com.synowkrz.sportrpg.View.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(sportRPGApp: SportRPGApp)
    fun inject(activity: UserActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: NewAccountActivity)
    fun inject(activity: TrainingActivity)
    fun inject(activity: CharacterActivity)
}