package com.synowkrz.sportrpg.View.Character

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.R
import javax.inject.Inject

class CharacterActivity : FragmentActivity(), CharacterView{

    val TAG = "KRZYS"

    @Inject
    lateinit var characterPresenter: CharacterPresenter
    var abilityFragment: AbilityView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        Log.d(TAG, "onCreate CharacterActivity")
        var fragment : Fragment = AbilityFragment.getInstance()
        fragment.arguments = intent.extras
        initDagger()
        characterPresenter.registerView(this)
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }

    override fun bindUserWithView(user: User) {
        abilityFragment?.bindUserWithView(user)
    }

    override fun setAllDecreaseButtonsVisibility(visible: Int) {
        abilityFragment?.setAllDecreaseButtonsVisibility(visible)
    }

    override fun setIncreaseButtonsVisibility(visible: Int) {
        abilityFragment?.setIncreaseButtonsVisibility(visible)
    }

    override fun setDecreaseButtonsVisibility(visible: Int, type: BasicAttributes) {
        abilityFragment?.setDecreaseButtonsVisibility(visible, type)
    }

    override fun setConfirmButtonVisibility(visible: Int) {
        abilityFragment?.setConfirmButtonVisibility(visible)
    }

    override fun setAllButtonsVisibility(visible: Int) {
        abilityFragment?.setAllButtonsVisibility(visible)
    }

    override fun initAbilityFragment(fragment: AbilityFragment) {
        abilityFragment = fragment
    }

    override fun getPresenter(): CharacterPresenter {
        return characterPresenter
    }

    private fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

    private fun unBindFragments() {
        abilityFragment = null
    }

}