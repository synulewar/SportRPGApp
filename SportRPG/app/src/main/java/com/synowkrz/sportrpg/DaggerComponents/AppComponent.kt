package com.synowkrz.sportrpg.DaggerComponents

import com.synowkrz.sportrpg.View.*
import com.synowkrz.sportrpg.View.Character.CharacterActivity
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