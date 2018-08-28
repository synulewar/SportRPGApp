package com.synowkrz.sportrpg.View.Character

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.synowkrz.sportrpg.Controller.CharacterPresenter
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.BasicAttributes
import com.synowkrz.sportrpg.Model.User
import com.synowkrz.sportrpg.R
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : FragmentActivity(), CharacterView{
    val TAG = "KRZYS"

    @Inject
    lateinit var characterPresenter: CharacterPresenter
    var abilityFragment: AbilityView? = null
    var skillFragment: SkillView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        Log.d(TAG, "onCreate CharacterActivity")
        var fragment : Fragment = AbilityFragment.getInstance()
        fragment.arguments = intent.extras
        initDagger()
        characterPresenter.registerView(this)
        prepareBottomMenu()
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

    override fun initSkillFragment(fragment: SkillFragment) {
        skillFragment = fragment
    }

    override fun getPresenter(): CharacterPresenter {
        return characterPresenter
    }

    private fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

    private fun unBindFragments() {
        abilityFragment = null
        skillFragment = null
    }

    private fun prepareBottomMenu() {
        bottomMenu.selectedItemId = R.id.action_abilities

        bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_home -> {
                    Log.d(TAG, "ET go home")
                    finish()
                }
                R.id.action_abilities -> {
                    Toast.makeText(applicationContext, "Abilities", Toast.LENGTH_LONG).show()
                    if (abilityFragment == null) {
                        var fragment = AbilityFragment.getInstance()
                        fragment.arguments = intent.extras
                        replaceFragment(fragment)
                    }
                }
                R.id.action_equipment -> {
                    Toast.makeText(applicationContext, "Equipment", Toast.LENGTH_LONG).show()
                }
                R.id.action_skills -> {
                    Toast.makeText(applicationContext, "Skills", Toast.LENGTH_LONG).show()
                    if (skillFragment == null) {
                        replaceFragment(SkillFragment.getInstance())
                    }
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        unBindFragments()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }


}