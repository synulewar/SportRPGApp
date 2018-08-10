package com.synowkrz.sportrpg.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.TrainingPresenter
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.ClockTime
import com.synowkrz.sportrpg.Model.TrainingStates
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class TrainingActivity : AppCompatActivity(), TrainingView {
    override fun startTraining() {
        trainingPresenter.startTraining()
    }

    override fun setButtons(trainingStates: TrainingStates) {
        Log.d(TAG, "setButtons " + trainingStates)
       when(trainingStates){
           TrainingStates.STOPPED -> {
               startResumeButton.text = getString(R.string.startButtonText)
               startResumeButton.setBackgroundColor(getColor(R.color.Green))
               finishStopButton.text = getString(R.string.finishButtonText)
               finishStopButton.setBackgroundColor(getColor(R.color.Red))
           }
           TrainingStates.IN_PROGRESS -> {
               startResumeButton.text = getString(R.string.pausedButtonText)
               startResumeButton.setBackgroundColor(getColor(R.color.Yellow))
               finishStopButton.text = getString(R.string.stopButtonText)
               finishStopButton.setBackgroundColor(getColor(R.color.Red))
           }
           TrainingStates.PAUSED -> {
               startResumeButton.text = getString(R.string.resumeButtonText)
               startResumeButton.setBackgroundColor(getColor(R.color.Green))
               finishStopButton.text = getString(R.string.stopButtonText)
               finishStopButton.setBackgroundColor(getColor(R.color.Red))
           }
       }
    }

    override fun setDistance(distance: Double) {
        distanceValue.text = "$distance km"
    }

    override fun setTime(clockTime: ClockTime) {
        timeValue.text = String.format("%02d:%02d:%02d", clockTime.hours, clockTime.minutes, clockTime.seconds)
    }

    override fun chooseTrainignImage(trainingType: TrainingType) {
        Log.d(TAG, "Trainig type  " + trainingType)
        when(trainingType) {
            TrainingType.RUN -> {trainingImage.setImageResource(R.drawable.run)}
            TrainingType.WALK -> {trainingImage.setImageResource(R.drawable.walk)}
            TrainingType.BIKE -> {trainingImage.setImageResource(R.drawable.bike)}
        }
    }

    val TAG = "KRZYS"

    @Inject
    lateinit var trainingPresenter: TrainingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Training on create")
        initDagger()
        setContentView(R.layout.activity_training)
        trainingPresenter.registerView(this)
        var trainingType = TrainingType.values()[intent.getIntExtra(ContractValues.ACTIVITY_TYPE_KEY, 0)]
        trainingPresenter.initTraining(trainingType)
        startResumeButton.setOnClickListener {trainingPresenter.onStartPausePressed()}
        finishStopButton.setOnClickListener {trainingPresenter.onStopFinishPressed()}
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }

}