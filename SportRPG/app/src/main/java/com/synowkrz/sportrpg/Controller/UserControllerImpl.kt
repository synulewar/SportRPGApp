package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Dao.UserDao
import dagger.Component
import javax.inject.Inject

class UserControllerImpl @Inject constructor(var userDao: UserDao) : UserController {

    val TAG = "KRZYS UserController"

    override fun getUserDataFromDb(name: String) : User {
        val user = userDao.getUser(name)
        Log.d(TAG, "Getting user " + user)
        return userDao.getUser(name)
    }

    override fun insertUserData(user: User) {
        Log.d(TAG,"Insert user " + user)
        userDao.insert(user)
    }
}