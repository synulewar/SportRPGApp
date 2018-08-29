package com.synowkrz.sportrpg.View.Character

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Model.MageSkills
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
    }

    override fun bindSkillFragmentData(skillList: List<Skill>) {
        for (skill in skillList) {
            when(skill.skillType) {
                SkillType.A0 -> skillPicture_A0.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.D0 -> skillPicture_D0.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.A1 -> skillPicture_A1.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.D1 -> skillPicture_D1.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.A2 -> skillPicture_A2.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.D2 -> skillPicture_D2.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.A3 -> skillPicture_A3.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.D3 -> skillPicture_D3.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.A4 -> skillPicture_A4.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.D4 -> skillPicture_D4.background = ContextCompat.getDrawable(mContext, MageSkills.getSkillDrawable(skill.name))
                SkillType.DEFAULT -> Log.d(TAG, "This should never happen")
            }
        }
    }
}