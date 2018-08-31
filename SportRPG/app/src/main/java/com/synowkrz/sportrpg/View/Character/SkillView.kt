package com.synowkrz.sportrpg.View.Character

import com.synowkrz.sportrpg.Model.Skill
import com.synowkrz.sportrpg.Model.SkillType

interface SkillView {
    fun bindSkillFragmentData(skillList : List<Skill>, visbilityMap: Map<SkillType, Int>, skillPoint: Int)
    fun showDetailDialog(ability: Int, skill: Skill)
}