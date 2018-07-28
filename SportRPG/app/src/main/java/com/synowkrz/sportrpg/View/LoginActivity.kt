package com.synowkrz.sportrpg.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val TAG = "KRZYS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        singIn.setOnClickListener { view ->  validateCredentials(username.text.toString(), password.text.toString()) }
    }

    fun validateCredentials(username: String, password: String) {
        Log.d(TAG, "Username " + username + "Password "+ password)
        if (true) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
}
