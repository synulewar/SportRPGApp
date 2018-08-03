package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.View.UserView
import javax.inject.Inject

class UserControllerImpl @Inject constructor(var userDao: UserDao) : UserController {



    var viewRegistered : Boolean = false
    lateinit var userView : UserView


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
        return userDao.getUser(email)
    }

    override fun insertUserData(user: User) {
        Log.d(TAG,"Insert user " + user)
        userDao.insert(user)
    }

    override fun loadUserData(email: String) {
        var user = userDao.getUser(email)
        userView.bindUserWithView(user)
    }

    override fun registerView(view: UserView) {
        userView = view
        viewRegistered = true
    }

    override fun unRegisterView() {
        viewRegistered = false
    }
}