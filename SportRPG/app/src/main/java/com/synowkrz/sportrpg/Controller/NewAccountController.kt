package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.View.NewAccountView

interface NewAccountController {

    fun addNewCredentials(credentials: Credentials)
    fun registerView(view: NewAccountView)
    fun unRegisterView()

}