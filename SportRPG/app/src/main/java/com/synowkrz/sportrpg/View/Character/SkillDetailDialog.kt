package com.synowkrz.sportrpg.View.Character

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDialog
import android.support.v7.app.AppCompatDialogFragment
import com.synowkrz.sportrpg.Model.CharacterSkills
import com.synowkrz.sportrpg.Model.Skill
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.skill_detail_dialog.*

class SkillDetailDialog constructor(context: Context, var affectingAbility: Int, var skill: Skill): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.skill_detail_dialog)
        dialogSkillName.text = skill.name
        dialogSkillPicture.background = ContextCompat.getDrawable(context, CharacterSkills.getSkillDrawable(skill.name))
        dialogSkillDescription.text = skill.description
        dialogCurrentLevel.text = String.format(context.getString(R.string.current_lelvel_dialog_string), skill.level)
        dialogCloseButton.setOnClickListener { dismiss() }
    }
}