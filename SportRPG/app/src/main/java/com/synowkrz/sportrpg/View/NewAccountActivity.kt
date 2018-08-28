package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.NewAccountController
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.CharacterType
import com.synowkrz.sportrpg.Model.Credentials
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_new_account.*
import javax.inject.Inject

class NewAccountActivity : Activity(), NewAccountView, AdapterView.OnItemSelectedListener {

    val TAG = "KRZYS"

    @Inject
    lateinit var newAccountController: NewAccountController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        initDagger()
        typeChooser.setOnItemSelectedListener(this)
        createNew.setOnClickListener {v ->
            var email = emailText.text.toString()
            var username = usernameText.text.toString()
            var password = passwordText.text.toString()
            var type : Int = Integer.parseInt(typeChooser.selectedItemId.toString())
            Log.d(TAG, "User input " + email + " " + username + " " + password + " " + type)
            newAccountController.addNewCredentials(Credentials(email, username, password, 0))
        }

    }

    private fun setHint() {
        Log.d(TAG, "setHint " + CharacterType.values()[typeChooser.selectedItemId.toInt()])
        when(CharacterType.values()[typeChooser.selectedItemId.toInt()]) {
            CharacterType.WARRIOR -> typeHint.text = getString(R.string.hint_warrior)
            CharacterType.MAGE -> typeHint.text = getString(R.string.hint_mage)
            CharacterType.ROGUE -> typeHint.text = getString(R.string.hint_rogue)
        }
    }

    override fun setResultAndFinish(result: Int) {
        setResult(result)
        finish()
    }

    override fun onResume() {
        super.onResume()
        newAccountController.registerView(this)
    }

    override fun onPause() {
        super.onPause()
        newAccountController.unRegisterView()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //Do nothiing
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        setHint()
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}