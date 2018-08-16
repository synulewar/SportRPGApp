package com.synowkrz.sportrpg.Controller

import android.util.Log
import android.view.View
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.AbilityType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.CharacterView
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenterImpl @Inject constructor(var userDao: UserDao) : CharacterPresenter {

    companion object {
        val VISIBLE = View.VISIBLE
        val INVISBILE = View.INVISIBLE
        val MOCK_USER_EMAIL = "mock@gmail.com"
        val MOCK_USER_NAME = "mockName"
    }

    val TAG = "KRZYS"
    lateinit var mainUser : User
    lateinit var characterView: CharacterView
    var lastConfirmedUser : User = User(MOCK_USER_EMAIL, MOCK_USER_NAME)

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
                .subscribe{ user ->
                    characterView.bindUserWithView(user)
                    characterView.setAllButtonsVisibility(INVISBILE)
                    if (mainUser.abilityPoints != 0) {
                        characterView.setIncreaseButtonsVisibility(VISIBLE)
                    }
                 }
    }

    override fun registerView(view: CharacterView) {
        characterView = view
    }

    override fun onConfirmButtonPressed() {
        userDao.insert(mainUser)
        lastConfirmedUser = User(MOCK_USER_EMAIL, MOCK_USER_NAME)
        characterView.setAllDecreaseButtonsVisibility(INVISBILE)
        characterView.setConfirmButtonVisibility(INVISBILE)
    }

    override fun onAbilityChange(type: AbilityType, increase: Boolean) {
        if (lastConfirmedUser.email.equals(MOCK_USER_EMAIL)) {
            lastConfirmedUser = mainUser.copy()
        }

        when(type) {
            AbilityType.STRENGTH -> if (increase) mainUser.strength += 1 else mainUser.strength -= 1
            AbilityType.AGILITY -> if (increase) mainUser.agility += 1 else mainUser.agility -= 1
            AbilityType.SPELL_POWER -> if (increase) mainUser.spellPower += 1 else mainUser.spellPower -= 1
            AbilityType.VITALITY -> if (increase) mainUser.vitality += 1 else mainUser.vitality -= 1
        }
        if (increase) mainUser.abilityPoints -= 1 else mainUser.abilityPoints += 1
        characterView.bindUserWithView(mainUser)
        resolvedButtonsVisibility()
    }

    private fun resolvedButtonsVisibility() {
        var visible : Int
        if (mainUser.abilityPoints == 0) visible = INVISBILE else visible = VISIBLE
        characterView.setIncreaseButtonsVisibility(visible)

        if (mainUser.strength == lastConfirmedUser.strength) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, AbilityType.STRENGTH)

        if (mainUser.agility == lastConfirmedUser.agility) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, AbilityType.AGILITY)

        if (mainUser.spellPower == lastConfirmedUser.spellPower) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, AbilityType.SPELL_POWER)

        if (mainUser.vitality == lastConfirmedUser.vitality) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, AbilityType.VITALITY)

        if (mainUser.equals(lastConfirmedUser))  visible = INVISBILE else visible = VISIBLE
        characterView.setConfirmButtonVisibility(visible)
    }

}