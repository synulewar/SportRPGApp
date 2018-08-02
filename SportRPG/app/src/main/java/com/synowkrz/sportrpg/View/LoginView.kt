package com.synowkrz.sportrpg.View

interface LoginView {
    fun updateCredentials(username: String, password: String)
    fun startUserActivity(username: String)
}