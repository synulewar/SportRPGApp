package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import android.util.Log
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Model.Credentials
import javax.inject.Inject

class NewAccountControllerImpl @Inject constructor(var credentialsDao: CredentialsDao, var sharedPreferences: SharedPreferences)
    : NewAccountController {

    val TAG = "KRZYS"

    override fun addNewCredentials(credentials: Credentials) : Boolean {
        if (knownCredentials(credentials)) {
            return false
        }

        credentialsDao.insertCredentials(credentials)


        return false
    }

    private fun knownCredentials(credentials: Credentials) : Boolean {
        var credentialsFromDB = credentialsDao.getCredentials(credentials.email)
        Log.d(TAG, "Credentials from DB " + credentialsFromDB)
        return credentialsFromDB != null
    }

    fun getCredentials(email: String) {
        credentialsDao.getCredentials(email)
    }



}