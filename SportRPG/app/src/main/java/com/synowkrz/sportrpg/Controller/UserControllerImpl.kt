package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.View.UserView
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserControllerImpl @Inject constructor(var userDao: UserDao) : UserController {
    override fun onCharacterItemClicked() {
        userView.startCharacterActivity(mainUser)
    }

    var viewRegistered : Boolean = false
    lateinit var userView : UserView
    lateinit var mainUser : User


    override fun deleteUser(userName: String) {
        userDao.deleteUser(userName)
    }

    override fun deleteAllUsers() {
        userDao.deleteAll()
    }

    val TAG = "KRZYS UserController"

    override fun getUserDataFromDb(email: String) : User {
        val user = userDao.getUser(email)
        Log.d(TAG, "Getting user " + user)
        mainUser = userDao.getUser(email)
        return mainUser
    }

    override fun insertUserData(user: User) {
        Log.d(TAG,"Insert user " + user)
        userDao.insert(user)
    }

    override fun loadUserData(email: String) {
        var maybeUser = Maybe.create<User> {emitter ->
            var user = userDao.getUser(email)
            if (user != null) {
                Log.d(TAG, "KRZYS onSuccess called")
                mainUser = user
                emitter.onSuccess(user)
            } else {
                emitter.onComplete()
            }
        }

        maybeUser
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ user -> userView.bindUserWithView(user) }
    }

    override fun registerView(view: UserView) {
        userView = view
        viewRegistered = true
    }

    override fun unRegisterView() {
        viewRegistered = false
    }

    override fun updateUserData(trainingType: TrainingType, time: Long, distance: Double, score: Long) {
        mainUser.addTrainingResulst(trainingType, time, distance, score)
        userDao.insert(mainUser)
        userView.bindUserWithView(mainUser)
    }
}