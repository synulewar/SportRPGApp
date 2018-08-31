package com.synowkrz.sportrpg.View.Character

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDialog
import android.support.v7.app.AppCompatDialogFragment
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import com.synowkrz.sportrpg.Model.BasicSkill
import com.synowkrz.sportrpg.Model.CharacterSkills
import com.synowkrz.sportrpg.Model.Skill
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.skill_detail_dialog.*
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.AppCompatTextView
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.skill_detail_dialog.view.*


class SkillDetailDialog constructor(context: Context, var affectingAbility: Int, var skill: Skill): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var titleLabel = findViewById<AppCompatTextView>(R.id.title)
        titleLabel?.text = skill.name
        titleLabel?.gravity = Gravity.CENTER_HORIZONTAL
        setContentView(R.layout.skill_detail_dialog)
        dialogSkillPicture.background = ContextCompat.getDrawable(context, CharacterSkills.getSkillDrawable(skill.name))
        dialogSkillDescription.text = skill.description
        dialogCurrentLevel.text = String.format(context.getString(R.string.current_lelvel_dialog_string), skill.level)
        dialogCloseButton.setOnClickListener { dismiss() }
        var basicSkillList = BasicSkill.convertStringIntoBasicSkillList(Skill.resolveSkillLevelAndAttribute(skill, affectingAbility).basicSkillList)
        var currentAdapter = EffectAdapter(basicSkillList, context)
        currentStats.adapter = currentAdapter

        var nextLevelSkill = skill.copy()
        nextLevelSkill.level = skill.level + 1
        var nextBasicSkillList = BasicSkill.convertStringIntoBasicSkillList(Skill.resolveSkillLevelAndAttribute(nextLevelSkill, affectingAbility).basicSkillList)
        var nextAdapter = EffectAdapter(nextBasicSkillList, context)
        nextLevelProgress.adapter = nextAdapter
    }

    private class EffectAdapter(): BaseAdapter() {

        lateinit var mData: List<BasicSkill>
        lateinit var inflater: LayoutInflater
        lateinit var mContext: Context


        constructor(effects: List<BasicSkill>, context: Context) :this() {
            mContext = context
            mData = effects
            inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        override fun getView(position: Int, view: View?, p2: ViewGroup?): View {
            var v = inflater.inflate(R.layout.effect_row, p2, false)
            var header = v?.findViewById<TextView>(R.id.rowHeader)
            var value = v?.findViewById<TextView>(R.id.rowValue)
            var basicSkill = mData[position]
            header?.text = mContext.getString(BasicSkill.getSecondaryAttributeName(basicSkill.effect)) + ":"
            value?.text = basicSkill.value.toString()
            return v
        }

        override fun getItem(p0: Int): Any {
            return mData[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mData.size
        }

    }
}

