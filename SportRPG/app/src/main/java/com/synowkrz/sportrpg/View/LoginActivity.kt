package com.synowkrz.sportrpg.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.LoginController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.Credentials

import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    val TAG = "KRZYS"

    @Inject
    lateinit var loginController: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initDagger()
        singIn.setOnClickListener { loginController.validateCredentials(Credentials("", usernameEdit.text.toString(), passwordEdit.text.toString()), this) }
        createNew.setOnClickListener {startNewAccountActivity()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ContractValues.NEW_ACCOUNT_ACTIVITY) {
            loginController.onNewAccountCreated(this)
        }
    }

    override fun updateCredentials(username: String, password: String) {
        usernameEdit.setText(username)
        passwordEdit.setText(password)
    }

    override fun startUserActivity(username: String) {
        var intent = Intent(this, UserActivity::class.java)
        intent.putExtra(ContractValues.USER_KEY, username)
        startActivity(intent)
    }

    fun startNewAccountActivity() {
        Log.d(TAG, "startNewAccountActivity")
        var intent = Intent(this, NewAccountActivity::class.java)
        startActivityForResult(intent, ContractValues.NEW_ACCOUNT_ACTIVITY)
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}
