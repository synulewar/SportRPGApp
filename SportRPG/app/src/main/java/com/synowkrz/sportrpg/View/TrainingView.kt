package com.synowkrz.sportrpg.View

import com.synowkrz.sportrpg.Model.ClockTime
import com.synowkrz.sportrpg.Model.Training
import com.synowkrz.sportrpg.Model.TrainingStates
import com.synowkrz.sportrpg.Model.TrainingType

interface TrainingView {
    fun setTime(clockTime: ClockTime)
    fun setDistance(distance : Double)
    fun chooseTrainignImage(trainingType: TrainingType)
    fun setButtons(trainingStates: TrainingStates)
    fun startTraining()
    fun displayFinalResults(training : Training)
    fun getCurrentLocation()
    fun startLocationTracking()
    fun stopLocationTracking()
}