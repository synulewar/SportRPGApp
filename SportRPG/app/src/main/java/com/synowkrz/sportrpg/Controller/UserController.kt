package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.User
import dagger.Component

interface UserController {

    fun getUserDataFromDb(name: String) : User
    fun insertUserData(user : User)
}