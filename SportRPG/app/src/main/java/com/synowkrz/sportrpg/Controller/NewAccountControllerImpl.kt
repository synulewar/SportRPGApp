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
        Log.d(TAG, String.format("Store crednetials %s in DB", credentials))
        credentialsDao.insertCredentials(credentials)
        userDao.insert(User(credentials.email, credentials.name))
        sharedPreferences.edit()
                .putString(ContractValues.STORED_EMAIL,  credentials.email)
                .putString(ContractValues.STORED_PASSWORD, credentials.password)
                .commit()
        var fetchCred = credentialsDao.getAllCredentials()
        for (credential in fetchCred) Log.d(TAG, credential.toString())
        return true
    }

    private fun knownCredentials(credentials: Credentials) : Boolean {
        Log.d(TAG, String.format("Check if crednetials %s in DB", credentials))
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