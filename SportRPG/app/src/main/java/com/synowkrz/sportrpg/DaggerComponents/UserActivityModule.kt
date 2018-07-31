package com.synowkrz.sportrpg.DaggerComponents

import com.synowkrz.sportrpg.View.UserActivity
import dagger.Module
import dagger.Provides

@Module
class UserActivityModule(private val activity: UserActivity) {

    @Provides
    fun providesUserActivity() = activity
}