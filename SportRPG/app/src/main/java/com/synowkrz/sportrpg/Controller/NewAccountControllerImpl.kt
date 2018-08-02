package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.Model.User
import javax.inject.Inject

class NewAccountControllerImpl @Inject constructor(var credentialsDao: CredentialsDao, var userDao: UserDao, var sharedPreferences: SharedPreferences)
    : NewAccountController {

    val TAG = "KRZYS"

    override fun addNewCredentials(credentials: Credentials) : Boolean {
        if (knownCredentials(credentials)) {
            Log.d(TAG, "bedzie false w credentialachh")
            return false
        }
        credentialsDao.insertCredentials(credentials)
        userDao.insert(User(credentials.name))
        sharedPreferences.edit()
                .putString(ContractValues.STORED_EMAIL,  credentials.name)
                .putString(ContractValues.STORED_PASSWORD, credentials.password)
                .commit()
        return true
    }

    private fun knownCredentials(credentials: Credentials) : Boolean {
        var credentialsFromDB = credentialsDao.getCredentials(credentials.email)
        Log.d(TAG, "Credentials from DB " + credentialsFromDB)
        return credentialsFromDB != null || checkCredentialsRemote()
    }

    fun checkCredentialsRemote() : Boolean {
        return false
    }

    fun createRemoteAccount() {
        TODO()
    }



}