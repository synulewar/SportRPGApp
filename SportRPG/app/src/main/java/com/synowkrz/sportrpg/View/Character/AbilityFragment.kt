package com.synowkrz.sportrpg.View.Character

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.ability_fragment.*

class AbilityFragment: Fragment(), AbilityView {

    val TAG = "KRZYS"

    var characterView: CharacterView? = null
    var characterPresenter: CharacterPresenter? = null

    companion object {
        fun getInstance() : AbilityFragment = AbilityFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView " )
        return inflater.inflate(R.layout.ability_fragment,
                container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated " )
        characterView = activity as CharacterView
        characterView?.initAbilityFragment(this)
        characterPresenter = characterView?.getPresenter()
        var email = arguments?.getString(ContractValues.EMAIL_KEY)
        Log.d(TAG, "emaik " + email )
        if (email != null) {
            characterPresenter?.loadUserData(email)
        }
        bindCharacterButtons()
    }

    override fun onAttach(context: Context?) {
        Log.d(TAG, "onAttach " )
        super.onAttach(context)
    }

    override fun bindUserWithView(user: User) {
        nameView.setText(user.name)
        avatarView.setImageResource(R.drawable.son);
        strengthValueView.text = user.strength.toString()
        agilityValueView.text = user.agility.toString()
        spellPowerValueView.text = user.spellPower.toString()
        vitalityValueView.text = user.vitality.toString()
        levelValueView.text = user.level.toString()
        abilityValueView.text = user.abilityPoints.toString()
        skillValueView.text = user.skillPoints.toString()
    }

    override fun setAllDecreaseButtonsVisibility(visible: Int) {
        for (i in 0..BasicAttributes.values().size - 1) {
            setDecreaseButtonsVisibility(visible, BasicAttributes.values()[i])
        }
    }

    override fun setIncreaseButtonsVisibility(visible: Int) {
        Log.d(TAG, "setIncreaseButtonsVisibility " + visible)
        buttonAddStrength.visibility = visible
        buttonAddAgility.visibility = visible
        buttonAddSpellPower.visibility = visible
        buttonAddVitality.visibility = visible
    }

    override fun setDecreaseButtonsVisibility(visible: Int, type: BasicAttributes) {
        Log.d(TAG, "setDecreaseButtonsVisibility " + type + " " + visible)
        when (type) {
            BasicAttributes.STRENGTH -> buttonSubstractStrength.visibility = visible
            BasicAttributes.AGILITY -> buttonSubstractAgility.visibility = visible
            BasicAttributes.SPELL_POWER -> buttonSubstractSpellPower.visibility = visible
            BasicAttributes.VITALITY -> buttonSubstractVitality.visibility =visible
        }
    }

    override fun setConfirmButtonVisibility(visible: Int) {
        buttonConfirm.visibility = visible
    }

    override fun setAllButtonsVisibility(visible: Int) {
        Log.d(TAG, "setAllButtonsVisibility " + visible)
        setIncreaseButtonsVisibility(visible)
        setAllDecreaseButtonsVisibility(visible)
        setConfirmButtonVisibility(visible)
    }


    private fun bindCharacterButtons() {

        buttonAddStrength.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.STRENGTH, true) }
        buttonAddAgility.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.AGILITY, true) }
        buttonAddSpellPower.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.SPELL_POWER, true) }
        buttonAddVitality.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.VITALITY, true) }

        buttonSubstractStrength.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.STRENGTH, false) }
        buttonSubstractAgility.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.AGILITY, false) }
        buttonSubstractSpellPower.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.SPELL_POWER, false) }
        buttonSubstractVitality.setOnClickListener { characterPresenter?.onAbilityChange(BasicAttributes.VITALITY, false) }

        buttonConfirm.setOnClickListener { characterPresenter?.onConfirmButtonPressed() }
    }


}