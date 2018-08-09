package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Dao.TrainingDao
import com.synowkrz.sportrpg.Model.ClockTime
import com.synowkrz.sportrpg.Model.TrainingStates
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.View.TrainingView
import javax.inject.Inject

class TrainingPresenterImpl @Inject constructor(trainingDao: TrainingDao) : TrainingPresenter {


    lateinit var trainingView: TrainingView
    val initialTrainingTime = 101236L;
    val initialTrainingDistance = 6.1


    override fun registerView(view: TrainingView) {
        trainingView = view
    }

    override fun initTraining(trainingType: TrainingType) {
        trainingView.chooseTrainignImage(trainingType)
        trainingView.setTime(ClockTime.clockFromMilis(initialTrainingTime))
        trainingView.setDistance(initialTrainingDistance)
        trainingView.setButtons(TrainingStates.STOPPED)
    }

    val TAG = "KRZYS"

    override fun startTraining() {
        Log.d(TAG, "Start training")
    }

}