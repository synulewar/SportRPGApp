package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.AbilityType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.CharacterView
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenterImpl @Inject constructor(var userDao: UserDao) : CharacterPresenter {
    override fun onAbilityChange(type: AbilityType, increase: Boolean) {
        when(type) {
            AbilityType.STRENGTH -> if (increase) mainUser.strength += 1 else mainUser.strength -= 1
            AbilityType.AGILITY -> if (increase) mainUser.agility += 1 else mainUser.agility -= 1
            AbilityType.SPELL_POWER -> if (increase) mainUser.spellPower += 1 else mainUser.spellPower -= 1
            AbilityType.VITALITY -> if (increase) mainUser.vitality += 1 else mainUser.vitality -= 1
        }
        if (increase) mainUser.abilityPoints -= 1 else mainUser.abilityPoints += 1
        characterView.bindUserWithView(mainUser)
    }

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