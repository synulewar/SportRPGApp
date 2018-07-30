package com.synowkrz.sportrpg.DaggerComponents

import com.synowkrz.sportrpg.View.LoginActivity
import com.synowkrz.sportrpg.View.UserActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(sportRPGApp: SportRPGApp)
    fun inject(userActivity: UserActivity)
    fun inject(loginActivity: LoginActivity)
}