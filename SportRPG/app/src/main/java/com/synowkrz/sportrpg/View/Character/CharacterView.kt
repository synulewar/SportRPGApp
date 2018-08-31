package com.synowkrz.sportrpg.View.Character

import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.Skill
import com.synowkrz.sportrpg.Model.SkillType
import com.synowkrz.sportrpg.Model.User

interface CharacterView {
    fun bindUserWithView(user: User)
    fun setIncreaseButtonsVisibility(visible: Int)
    fun setDecreaseButtonsVisibility(visible: Int, type: BasicAttributes)
    fun setConfirmButtonVisibility(visible: Int)
    fun setAllButtonsVisibility(visible: Int)
    fun setAllDecreaseButtonsVisibility(visible: Int)
    fun initAbilityFragment(fragment: AbilityFragment)
    fun getPresenter(): CharacterPresenter
    fun initSkillFragment(fragment: SkillFragment)
    fun bindSkillFragmentData(skillList : List<Skill>, visbilityMap: Map<SkillType, Int>, skillPoint: Int)
    fun showDetailDialog(ability: Int, skill: Skill)
}