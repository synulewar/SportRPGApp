package com.synowkrz.sportrpg.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.TrainingPresenter
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class TrainingActivity : AppCompatActivity() {

    val TAG = "KRZYS"

    @Inject
    lateinit var trainingPresenter: TrainingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Training on create")
        initDagger()
        trainingPresenter.startTraining()
        setContentView(R.layout.activity_training)
        var trainingType = TrainingType.values()[intent.getIntExtra(ContractValues.ACTIVITY_TYPE_KEY, 0)]
        trainignName.text = trainingType.toString()
    }


    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}