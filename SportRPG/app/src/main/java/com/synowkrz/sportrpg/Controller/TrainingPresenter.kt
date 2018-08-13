package com.synowkrz.sportrpg.Controller

import android.location.Location
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.View.TrainingView

interface TrainingPresenter {
    fun initTraining(trainingType: TrainingType)
    fun startTraining()
    fun registerView(view : TrainingView)
    fun onStartPausePressed()
    fun onStopFinishPressed()
    fun startTimerAndDistanceTracker()
    fun onLocationChanged(location : Location)
}