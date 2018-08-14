package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.UserView
import dagger.Component

interface UserController {

    fun loadUserData(email: String)
    fun getUserDataFromDb(email: String) : User
    fun insertUserData(user : User)
    fun deleteUser(userName: String)
    fun deleteAllUsers()
    fun registerView(view: UserView)
    fun unRegisterView()
    fun updateUserData(trainingType: TrainingType, time: Long, distance: Double, score: Long)
}