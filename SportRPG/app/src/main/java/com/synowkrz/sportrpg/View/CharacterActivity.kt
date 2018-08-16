package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.AbilityType
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_character.*
import javax.inject.Inject

class CharacterActivity : Activity(), CharacterView {

    val TAG = "KRZYS"

    @Inject
    lateinit var characterPresenter: CharacterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        Log.d(TAG, "onCreate CharacterActivity")
        var email = intent.getStringExtra(ContractValues.EMAIL_KEY)
        initDagger()
        characterPresenter.registerView(this)
        characterPresenter.loadUserData(email)
        bindCharacterButtons()
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
        for (i in 0..AbilityType.values().size - 1) {
            setDecreaseButtonsVisibility(visible, AbilityType.values()[i])
        }
    }

    override fun setIncreaseButtonsVisibility(visible: Int) {
        Log.d(TAG, "setIncreaseButtonsVisibility " + visible)
        buttonAddStrength.visibility = visible
        buttonAddAgility.visibility = visible
        buttonAddSpellPower.visibility = visible
        buttonAddVitality.visibility = visible
    }

    override fun setDecreaseButtonsVisibility(visible: Int, type: AbilityType) {
        Log.d(TAG, "setDecreaseButtonsVisibility " + type + " " + visible)
        when (type) {
            AbilityType.STRENGTH -> buttonSubstractStrength.visibility = visible
            AbilityType.AGILITY -> buttonSubstractAgility.visibility = visible
            AbilityType.SPELL_POWER -> buttonSubstractSpellPower.visibility = visible
            AbilityType.VITALITY -> buttonSubstractVitality.visibility =visible
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

    private fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

    private fun bindCharacterButtons() {
        
        buttonAddStrength.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.STRENGTH, true) }
        buttonAddAgility.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.AGILITY, true) }
        buttonAddSpellPower.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.SPELL_POWER, true) }
        buttonAddVitality.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.VITALITY, true) }

        buttonSubstractStrength.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.STRENGTH, false) }
        buttonSubstractAgility.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.AGILITY, false) }
        buttonSubstractSpellPower.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.SPELL_POWER, false) }
        buttonSubstractVitality.setOnClickListener { characterPresenter.onAbilityChange(AbilityType.VITALITY, false) }

        buttonConfirm.setOnClickListener { characterPresenter.onConfirmButtonPressed() }
    }
}