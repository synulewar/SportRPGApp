package com.synowkrz.sportrpg.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.LoginController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp

import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    val TAG = "KRZYS"

    @Inject
    lateinit var loginController: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initDagger()
        singIn.setOnClickListener { startUserActivity(username.text.toString(), password.text.toString()) }
        createNew.setOnClickListener {startNewAccountActivity()}
    }

    fun startUserActivity(username: String, password: String) {
        Log.d(TAG, "Username " + username + " Password "+ password)
        if (loginController.validateCredentials()) {
           var intent = Intent(this, UserActivity::class.java)
            intent.putExtra(ContractValues.USER_KEY, username)
            startActivity(intent)
        }
    }

    fun startNewAccountActivity() {
        Log.d(TAG, "startNewAccountActivity")
        if (loginController.validateCredentials()) {
            var intent = Intent(this, NewAccountActivity::class.java)
            startActivityForResult(intent, ContractValues.NEW_ACCOUNT_ACTIVITY)
        }
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}
