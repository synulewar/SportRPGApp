package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.Controller.UserControllerImpl
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Database.SportRPGDatabase
import com.synowkrz.sportrpg.Model.User
import kotlinx.android.synthetic.main.user_main_layout.*
import javax.inject.Inject

class UserActivity : Activity() {

    val TAG = "KRZYS"

    @Inject
    lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_main_layout)
        var username = intent.extras.getString(ContractValues.USER_KEY)
        Log.d(TAG, "Start user activity with username " + username)
        (application as SportRPGApp).initDaggerFromUserActivity(this)
        var user = User(ContractValues.KRZYSIO, 21.1, 8.7, 30.1, 1456)
        userController.insertUserData(user)
        loadUserScreen()
    }

    fun loadUserScreen() {
        avatarView.setImageResource(R.drawable.son);
        progressBarView.max = 1000
        progressBarView.progress = 245
        userController.getUserDataFromDb(ContractValues.KRZYSIO)
    }


}