package com.synowkrz.sportrpg.DaggerComponents

import android.app.Activity
import android.app.Application
import com.synowkrz.sportrpg.View.UserActivity

class SportRPGApp : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        graph.inject(this)
    }

    fun getAppComponent() : AppComponent {
        return graph
    }
}