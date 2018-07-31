package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_account.*

class NewAccountActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNew.setOnClickListener { kotlin.run {
            var email = emailText.text.toString()
            var username = usernameText.text.toString()
            var password = passwordText.text.toString()

            if (validateCredentials(email, username, password))

        } }

    }

    private fun validateCredentials(email: String, username: String, password: String) =
            email.length > 0 && username.length > 0 && password.length > 0


}