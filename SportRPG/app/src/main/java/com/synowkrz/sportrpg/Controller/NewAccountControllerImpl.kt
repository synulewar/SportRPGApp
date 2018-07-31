package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.Credentials
import javax.inject.Inject

class NewAccountControllerImpl @Inject constructor(var credentialsDao: CredentialsDao, var sharedPreferences: SharedPreferences)
    : NewAccountController {

    override fun validateCredentials(credentials: Credentials) : Boolean {

        return false
    }


}