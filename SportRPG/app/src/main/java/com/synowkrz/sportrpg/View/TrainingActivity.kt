package com.synowkrz.sportrpg.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_training.*

class TrainingActivity : AppCompatActivity() {

    val TAG = "KRZYS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Training on create")
        setContentView(R.layout.activity_training)
        var fragment = ChooseTrainingFragment()
        replaceFragment(fragment)
    }


    fun replaceFragment(fragment: Fragment) {
        Log.d(TAG, "replaceTarget")
        var fragmetnTransaction = supportFragmentManager.beginTransaction()
        fragmetnTransaction.add(R.id.fragmentContainer, fragment)
        fragmetnTransaction.commit()
    }
}