package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.View.LoginView
import javax.inject.Inject

class LoginControllerImpl @Inject constructor(var sharedPreferences: SharedPreferences, var credentialsDao: CredentialsDao) : LoginController {

    val TAG = "KRZYS"

    lateinit var loginView: LoginView
    var viewRegistered : Boolean = false
    override fun registerView(view: LoginView) {
        loginView = view
        viewRegistered = true
        Log.d(TAG, "registerView")
    }

    override fun unRegisterView() {
        Log.d(TAG, "unRegisterView")
        viewRegistered = false
    }

    override fun validateCredentials(credentials: Credentials) {
        var credentialsFromDB = credentialsDao.getCredentials(credentials.email)
        Log.d(TAG, "Credentials from DB " + credentialsFromDB + " credential from user " + credentials)
        if (credentialsFromDB != null && credentialsFromDB.password == credentials.password && viewRegistered) {
            loginView.startUserActivity(credentials.email)
        }
    }

    override fun updateLogin() {
        var email = sharedPreferences.getString(ContractValues.STORED_EMAIL, "")
        var password = sharedPreferences.getString(ContractValues.STORED_PASSWORD, "")
        Log.d(TAG, "updateLogin " + email + " " + password)
        loginView.updateCredentials(email, password)
    }
}