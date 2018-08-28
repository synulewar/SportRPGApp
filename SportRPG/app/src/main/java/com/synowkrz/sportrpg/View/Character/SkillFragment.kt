package com.synowkrz.sportrpg.View.Character

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.R

class SkillFragment: Fragment(), SkillView {

    val TAG = "KRZYS"

    var characterView: CharacterView? = null
    var characterPresenter: CharacterPresenter? = null

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
    }
}