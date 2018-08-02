package com.synowkrz.sportrpg.Controller

import android.content.SharedPreferences
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Dao.CredentialsDao
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.View.LoginView
import javax.inject.Inject

class LoginControllerImpl @Inject constructor(var sharedPreferences: SharedPreferences, var credentialsDao: CredentialsDao) : LoginController {
    override fun validateCredentials(credentials: Credentials, loginView: LoginView) {
        var credentialsFromDB = credentialsDao.getCredentials(credentials.name)
        if (credentialsFromDB != null) {
            if (credentialsFromDB.password == credentials.password) {
                loginView.startUserActivity(credentials.name)
            }
        }



    }

    override fun onNewAccountCreated(loginView: LoginView) {
        var username = sharedPreferences.getString(ContractValues.STORED_USERNAME, "")
        var password = sharedPreferences.getString(ContractValues.STORED_PASSWORD, "")
        loginView.updateCredentials(username, password)
    }


}