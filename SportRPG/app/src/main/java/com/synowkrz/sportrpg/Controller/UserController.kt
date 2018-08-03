package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.User
import dagger.Component

interface UserController {

    fun getUserDataFromDb(email: String) : User
    fun insertUserData(user : User)
    fun deleteUser(userName: String)
    fun deleteAllUsers()
}