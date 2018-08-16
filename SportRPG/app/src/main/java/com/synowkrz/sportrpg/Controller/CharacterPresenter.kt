package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.AbilityType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.View.CharacterView
import com.synowkrz.sportrpg.View.UserView

interface CharacterPresenter  {
    fun loadUserData(email: String)
    fun registerView(view: CharacterView)
    fun onAbilityChange(type: AbilityType, increase: Boolean)
    fun onConfirmButtonPressed()
}