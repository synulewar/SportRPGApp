package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.Credentials

interface NewAccountController {

    fun addNewCredentials(credentials: Credentials) : Boolean

}