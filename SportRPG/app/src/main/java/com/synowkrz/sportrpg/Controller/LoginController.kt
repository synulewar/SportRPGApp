package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.View.LoginView

interface LoginController {

    fun validateCredentials(credentials: Credentials)
    fun updateLogin()
    fun registerView(view: LoginView)
    fun unRegisterView()

}