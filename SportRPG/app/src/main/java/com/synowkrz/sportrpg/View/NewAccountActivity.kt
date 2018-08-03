package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.NewAccountController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_new_account.*
import javax.inject.Inject

class NewAccountActivity : Activity(), NewAccountView {

    val TAG = "KRZYS"

    @Inject
    lateinit var newAccountController: NewAccountController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        initDagger()

        createNew.setOnClickListener {v ->
            var email = emailText.text.toString()
            var username = usernameText.text.toString()
            var password = passwordText.text.toString()
            Log.d(TAG, "User input " + email + " " + username + " " + password)
            newAccountController.addNewCredentials(Credentials(email, username, password))
        }

    }

    override fun setResultAndFinish(result: Int) {
        setResult(result)
        finish()
    }

    override fun onResume() {
        super.onResume()
        newAccountController.registerView(this)
    }

    override fun onPause() {
        super.onPause()
        newAccountController.unRegisterView()
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}