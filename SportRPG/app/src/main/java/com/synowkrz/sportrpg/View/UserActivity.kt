package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.User
import kotlinx.android.synthetic.main.user_main_layout.*
import javax.inject.Inject

class UserActivity : Activity(), UserView {

    val TAG = "KRZYS"

    @Inject
    lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_main_layout)
        initDagger()
        var email = intent.extras.getString(ContractValues.EMAIL_KEY)
        Log.d(TAG, "Start user activity with email" + email)
        userController.registerView(this)
        userController.loadUserData(email)
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

    override
    fun bindUserWithView(user: User) {
        nameView.setText(user.name)
        scoreValueView.setText(user.score.toString())
        walkValueView.setText(user.walkDis.toString())
        runValueView.setText(user.runDis.toString())
        bikeValueView.setText(user.bikeDis.toString())
        avatarView.setImageResource(R.drawable.son);
        progressBarView.max = 1000
        progressBarView.progress = 245
    }

    override fun onResume() {
        super.onResume()
        userController.registerView(this)
    }

    override fun onPause() {
        super.onPause()
        userController.unRegisterView()
    }
}