package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.CharacterView
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenterImpl @Inject constructor(var userDao: UserDao) : CharacterPresenter {

    val TAG = "KRZYS"
    lateinit var mainUser : User
    lateinit var characterView: CharacterView

    override fun loadUserData(email: String) {
        var maybeUser = Maybe.create<User> { emitter ->
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
                .subscribe{ user -> characterView.bindUserWithView(user) }
    }

    override fun registerView(view: CharacterView) {
        characterView = view
    }



}