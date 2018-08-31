package com.synowkrz.sportrpg.Controller

import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.SkillType
import com.synowkrz.sportrpg.View.Character.CharacterView

interface CharacterPresenter  {
    fun loadUserData(email: String)
    fun registerView(view: CharacterView)
    fun onAbilityChange(type: BasicAttributes, increase: Boolean)
    fun onConfirmButtonPressed()
    fun onSkillFragmentCreated()
    fun onSkillChange(skillType: SkillType)
    fun onSkillPictureClicked(skillType: SkillType)
}