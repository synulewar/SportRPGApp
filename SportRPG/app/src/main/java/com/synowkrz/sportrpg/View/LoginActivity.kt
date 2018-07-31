package com.synowkrz.sportrpg.View

import android.app.Application
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.DaggerComponents.AppComponent
import com.synowkrz.sportrpg.DaggerComponents.AppModule
import com.synowkrz.sportrpg.DaggerComponents.DaggerAppComponent

import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    val TAG = "KRZYS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        singIn.setOnClickListener { view ->  validateCredentials(username.text.toString(), password.text.toString()) }
    }

    fun validateCredentials(username: String, password: String) {
        Log.d(TAG, "Username " + username + " Password "+ password)
        if (true) {
           var intent = Intent(this, UserActivity::class.java)
            intent.putExtra(ContractValues.USER_KEY, username)
            startActivity(intent)
        }
    }

}
