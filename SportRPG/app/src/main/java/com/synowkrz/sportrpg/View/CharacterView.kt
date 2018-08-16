package com.synowkrz.sportrpg.View

import com.synowkrz.sportrpg.Model.AbilityType
import com.synowkrz.sportrpg.Model.User

interface CharacterView {
    fun bindUserWithView(user: User)
    fun setIncreaseButtonsVisibility(visible: Int)
    fun setDecreaseButtonsVisibility(visible: Int, type: AbilityType)
    fun setConfirmButtonVisibility(visible: Int)
    fun setAllButtonsVisibility(visible: Int)
}