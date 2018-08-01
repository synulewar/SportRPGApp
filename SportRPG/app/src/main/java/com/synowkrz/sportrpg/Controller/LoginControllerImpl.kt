package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import com.synowkrz.sportrpg.Dao.CredentialsDao
import javax.inject.Inject

class LoginControllerImpl @Inject constructor(sharedPreferences: SharedPreferences, credentialsDao: CredentialsDao) : LoginController {
    override fun validateCredentials(): Boolean {


        return true
    }

}