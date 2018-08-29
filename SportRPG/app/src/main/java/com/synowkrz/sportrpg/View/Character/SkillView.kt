package com.synowkrz.sportrpg.View.Character

import com.synowkrz.sportrpg.Model.Skill

interface SkillView {
    fun bindSkillFragmentData(skillList : List<Skill>)
}