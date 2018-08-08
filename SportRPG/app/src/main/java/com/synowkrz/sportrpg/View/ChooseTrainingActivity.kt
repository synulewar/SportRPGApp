package com.synowkrz.sportrpg.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_chooser_training.*


class ChooseTrainingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser_training)
        runButton.setOnClickListener { sendResultData(TrainingType.RUN) }
        bikeButton.setOnClickListener { sendResultData(TrainingType.BIKE)}
        walkButton.setOnClickListener { sendResultData(TrainingType.WALK) }
    }

    fun sendResultData(trainingType : TrainingType) {
        var intent = Intent()
        intent.putExtra(ContractValues.ACTIVITY_TYPE_KEY, trainingType.ordinal)
        setResult(RESULT_OK, intent)
        finish()
    }
}