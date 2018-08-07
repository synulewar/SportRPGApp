package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Constant.Level
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
        bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_training -> {
                    Toast.makeText(applicationContext, "Training", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.action_character -> {
                    Toast.makeText(applicationContext, "Character", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.action_dungeon -> {
                    Toast.makeText(applicationContext, "Dungeon", Toast.LENGTH_LONG).show()
                    true
                }
            }
            false
        }
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
        levelValueView.setText(user.level.toString())
        progressValueView.setText(String.format("%d/%d", user.experience, Level.levelUpLimit(user.level)))
        progressBarView.progress = user.experience
        progressBarView.max = Level.levelUpLimit(user.level)
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