package com.synowkrz.sportrpg.View

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.synowkrz.sportrpg.R
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Constant.Level
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.Character.CharacterActivity
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
                    Log.d(TAG, "startTrainingActivity")
                    verifyPermissionsAndStartTraining()
                }
                R.id.action_character -> {
                    Toast.makeText(applicationContext, "Character", Toast.LENGTH_LONG).show()
                    userController.onCharacterItemClicked()
                }
                R.id.action_dungeon -> {
                    Toast.makeText(applicationContext, "Dungeon", Toast.LENGTH_LONG).show()
                }
            }
            true
        }
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

    override
    fun bindUserWithView(user: User) {
        nameView.setText(user.name)
        scoreValueView.setText(user.score.toString())
        walkValueView.text = "%.2f km".format(user.walkDis)
        runValueView.text = "%.2f km".format(user.runDis)
        bikeValueView.text = "%.2f km".format(user.bikeDis)
        avatarView.setImageResource(R.drawable.son);
        levelValueView.setText(user.level.toString())
        var levelProgress = Level.calculateLevelProgress(user.experience, user.level)
        var levelUpLimit = Level.levelUpLimit(user.level)
        progressValueView.setText(String.format("%d/%d", levelProgress, levelUpLimit))
        progressBarView.progress = levelProgress.toInt()
        progressBarView.max = levelUpLimit.toInt()
    }


    fun startChooserActivity() {
        Log.d(TAG, "startNewAccountActivity")
        var intent = Intent(this, ChooseTrainingActivity::class.java)
        startActivityForResult(intent, ContractValues.CHOOSE_TRAINING_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ContractValues.CHOOSE_TRAINING_ACTIVITY) {
            if (data != null) {
                var trainingType = data.getIntExtra(ContractValues.ACTIVITY_TYPE_KEY, 0)
                var intent = Intent(this, TrainingActivity::class.java)
                intent.putExtra(ContractValues.ACTIVITY_TYPE_KEY, trainingType)
                startActivityForResult(intent, ContractValues.TRAINING_ACTIVITY)
            }
        } else if (requestCode == ContractValues.TRAINING_ACTIVITY) {
            Toast.makeText(applicationContext, "Training finished!", Toast.LENGTH_LONG).show()
            if (data != null) {
                var distance = data.getDoubleExtra(ContractValues.DISTANCE_KEY, 0.0)
                var time = data.getLongExtra(ContractValues.TIME_KEY, 0L)
                var score = data.getIntExtra(ContractValues.SCORE_KEY, 0)
                var type = TrainingType.values()[data.getIntExtra(ContractValues.TYPE_KEY,0)]
                Log.d(TAG, "distance " + distance + " time " + time + " score " + score + " type " + type)
                userController.updateUserData(type, time, distance, score.toLong())
            }
        } else if (requestCode == ContractValues.CHARACTER_ACTIVITY) {
            Log.d(TAG, "Refresh user data!")
            userController.reloadUserData()
        }
    }

    override fun onResume() {
        super.onResume()
        userController.registerView(this)
    }

    override fun onPause() {
        super.onPause()
        userController.unRegisterView()
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        Log.d(TAG, "on request result")

        for (i in 0..permissions.size - 1) {
            Log.d(TAG,permissions[i] + " " + grantResults[i])
        }
        when (requestCode) {
            ContractValues.PERMISSION_REQUEST -> {
                if ((grantResults.isNotEmpty())) {
                    for (decision in grantResults) {
                        if (decision != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(applicationContext, "App wont work without permissions", Toast.LENGTH_LONG).show()
                            return
                        }
                    }
                    startChooserActivity()
                }

            }
            else -> {
                Log.e(TAG, "Wrong permission code")
            }
        }
    }

    override fun startCharacterActivity(user: User) {
        var intent = Intent(this, CharacterActivity::class.java)
        intent.putExtra(ContractValues.EMAIL_KEY, user.email)
        startActivityForResult(intent, ContractValues.CHARACTER_ACTIVITY)
    }

    private fun verifyPermissionsAndStartTraining() {
        var location = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        var storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if (!location or !storage) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE), ContractValues.PERMISSION_REQUEST)
        } else {
            startChooserActivity()
        }
    }

    override fun onBackPressed() {
        Log.d(TAG, "Block on back pressed")
    }
}