package com.synowkrz.sportrpg.View.Character

import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.User

interface AbilityView {
    fun bindUserWithView(user: User)
    fun setIncreaseButtonsVisibility(visible: Int)
    fun setDecreaseButtonsVisibility(visible: Int, type: BasicAttributes)
    fun setConfirmButtonVisibility(visible: Int)
    fun setAllButtonsVisibility(visible: Int)
    fun setAllDecreaseButtonsVisibility(visible: Int)
}