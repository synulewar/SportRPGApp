package com.synowkrz.sportrpg.View.Character

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Model.CharacterSkills
import com.synowkrz.sportrpg.Model.Skill
import com.synowkrz.sportrpg.Model.SkillType
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.skill_fragment.*

class SkillFragment: Fragment(), SkillView {

    val TAG = "KRZYS"

    var characterView: CharacterView? = null
    var characterPresenter: CharacterPresenter? = null
    lateinit var mContext: Context

    companion object {
        fun getInstance() : SkillFragment = SkillFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView " )
        return inflater.inflate(R.layout.skill_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated " )
        characterView = activity as CharacterView
        characterView?.initSkillFragment(this)
        characterPresenter = characterView?.getPresenter()
        if (context != null) {
            mContext = context as Context
        }
        characterPresenter?.onSkillFragmentCreated()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        buttonSkillLvlUp_A0.setOnClickListener { characterPresenter?.onSkillChange(SkillType.A0) }
        buttonSkillLvlUp_A1.setOnClickListener { characterPresenter?.onSkillChange(SkillType.A1) }
        buttonSkillLvlUp_A2.setOnClickListener { characterPresenter?.onSkillChange(SkillType.A2) }
        buttonSkillLvlUp_A3.setOnClickListener { characterPresenter?.onSkillChange(SkillType.A3) }
        buttonSkillLvlUp_A4.setOnClickListener { characterPresenter?.onSkillChange(SkillType.A4) }

        buttonSkillLvlUp_D0.setOnClickListener { characterPresenter?.onSkillChange(SkillType.D0) }
        buttonSkillLvlUp_D1.setOnClickListener { characterPresenter?.onSkillChange(SkillType.D1) }
        buttonSkillLvlUp_D2.setOnClickListener { characterPresenter?.onSkillChange(SkillType.D2) }
        buttonSkillLvlUp_D3.setOnClickListener { characterPresenter?.onSkillChange(SkillType.D3) }
        buttonSkillLvlUp_D4.setOnClickListener { characterPresenter?.onSkillChange(SkillType.D4) }
    }

    override fun bindSkillFragmentData(skillList : List<Skill>, visbilityMap: Map<SkillType, Int>, skillPoint: Int) {
        skillValueView.text = skillPoint.toString()

        for (skill in skillList) {
            when(skill.skillType) {
                SkillType.A0 -> {
                    skillPicture_A0.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_A0.text =skill.name
                    skillLevel_A0.text = skill.level.toString()
                    buttonSkillLvlUp_A0.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.D0 -> {
                    skillPicture_D0.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_D0.text =skill.name
                    skillLevel_D0.text = skill.level.toString()
                    buttonSkillLvlUp_D0.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.A1 -> {
                    skillPicture_A1.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_A1.text =skill.name
                    skillLevel_A1.text = skill.level.toString()
                    buttonSkillLvlUp_A1.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.D1 -> {
                    skillPicture_D1.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_D1.text =skill.name
                    skillLevel_D1.text = skill.level.toString()
                    buttonSkillLvlUp_D1.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.A2 -> {
                    skillPicture_A2.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_A2.text =skill.name
                    skillLevel_A2.text = skill.level.toString()
                    buttonSkillLvlUp_A2.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.D2 -> {
                    skillPicture_D2.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_D2.text =skill.name
                    skillLevel_D2.text = skill.level.toString()
                    buttonSkillLvlUp_D2.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.A3 -> {
                    skillPicture_A3.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_A3.text =skill.name
                    skillLevel_A3.text = skill.level.toString()
                    buttonSkillLvlUp_A3.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.D3 -> {
                    skillPicture_D3.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_D3.text =skill.name
                    skillLevel_D3.text = skill.level.toString()
                    buttonSkillLvlUp_D3.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.A4 -> {
                    skillPicture_A4.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_A4.text =skill.name
                    skillLevel_A4.text = skill.level.toString()
                    buttonSkillLvlUp_A4.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.D4 -> {
                    skillPicture_D4.background = ContextCompat.getDrawable(mContext, CharacterSkills.getSkillDrawable(skill.name))
                    skillName_D4.text =skill.name
                    skillLevel_D4.text = skill.level.toString()
                    buttonSkillLvlUp_D4.visibility = visbilityMap.getOrDefault(skill.skillType, View.INVISIBLE)
                }
                SkillType.DEFAULT -> Log.d(TAG, "This should never happen")
            }
        }
    }
}