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
        Log.d(TAG, "Login onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initDagger()
        singIn.setOnClickListener { loginController.validateCredentials(Credentials(emailEdit.text.toString(), passwordEdit.text.toString())) }
        createNew.setOnClickListener {startNewAccountActivity()}
        loginController.registerView(this)
        loginController.updateLogin()
        loginController.validateCredentials(Credentials(emailEdit.text.toString(), passwordEdit.text.toString()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ContractValues.NEW_ACCOUNT_ACTIVITY) {
            loginController.updateLogin()
        }
    }

    override fun updateCredentials(email: String, password: String) {
        Log.d(TAG, "updateCredentials " + email + " " + password)
        emailEdit.setText(email)
        passwordEdit.setText(password)
    }

    override fun startUserActivity(email: String) {
        Log.d(TAG, "startUserActivity")
        var intent = Intent(this, UserActivity::class.java)
        intent.putExtra(ContractValues.EMAIL_KEY, email)
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

    override fun onResume() {
        super.onResume()
        loginController.registerView(this)
    }

    override fun onPause() {
        super.onPause()
        loginController.unRegisterView()
    }

}
