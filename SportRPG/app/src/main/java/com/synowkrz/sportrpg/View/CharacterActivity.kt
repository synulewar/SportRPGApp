package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.Controller.UserController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
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

    }

    override
    fun bindUserWithView(user: User) {
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

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }
}