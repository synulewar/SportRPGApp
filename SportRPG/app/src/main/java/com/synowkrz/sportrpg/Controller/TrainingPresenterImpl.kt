package com.synowkrz.sportrpg.Controller

import android.location.Location
import android.os.SystemClock
import android.util.Log
import com.synowkrz.sportrpg.Dao.TrainingDao
import com.synowkrz.sportrpg.Location.DistanceHelper
import com.synowkrz.sportrpg.Location.DistanceHelperImpl
import com.synowkrz.sportrpg.Model.ClockTime
import com.synowkrz.sportrpg.Model.Training
import com.synowkrz.sportrpg.Model.TrainingStates
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.View.TrainingView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TrainingPresenterImpl @Inject constructor(trainingDao: TrainingDao) : TrainingPresenter {

    var distanceHelper : DistanceHelper = DistanceHelperImpl.getInstance()
    lateinit var trainingView: TrainingView
    lateinit var disposableTimer : Disposable
    lateinit var stroredTrainingType: TrainingType
    var trainingTime = 0L;
    var initialTrainingDistance = 6.1
    var currentTrainingState : TrainingStates = TrainingStates.STOPPED
    var trainingStartTimestamp : Long = 0L
    var timerRX : Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)

    override fun onLocationChanged(location: Location) {
        distanceHelper.addNewLocation(location)
    }

    override fun onStopFinishPressed() {
        when(currentTrainingState) {
            TrainingStates.IN_PROGRESS -> {
                disposableTimer.dispose()
                trainingView.stopLocationTracking()
                currentTrainingState = TrainingStates.STOPPED
                trainingView.setButtons(TrainingStates.STOPPED)
            }
            TrainingStates.PAUSED -> {
                currentTrainingState = TrainingStates.STOPPED
                trainingView.setButtons(TrainingStates.STOPPED)
            }
            TrainingStates.STOPPED -> {
                var training = Training(1,"synulewar@gmail.com", stroredTrainingType.ordinal)
                training.distance = distanceHelper.getTotalDsitance()
                training.time = trainingTime
                training.calcResults()
                trainingView.displayFinalResults(training)
            }
        }
    }

    override fun startTimerAndDistanceTracker() {
        Log.d(TAG,  "Initial training time " + trainingTime)
        trainingStartTimestamp = SystemClock.uptimeMillis() - trainingTime
        Log.d(TAG,  "Subscribe timer rx")
        disposableTimer = timerRX
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    var currentTime = SystemClock.uptimeMillis()
                    trainingTime = currentTime - trainingStartTimestamp
                    Log.d(TAG, "timer event startTime " + trainingStartTimestamp + " current time " +  currentTime + " diff " + trainingTime)
                    trainingView.setTime(ClockTime.clockFromMilis(trainingTime))
                    trainingView.setDistance(distanceHelper.getTotalDsitance())
                }
        trainingView.startLocationTracking()
    }

    override fun onStartPausePressed() {

        Log.d(TAG, "onStratPausePressed " + currentTrainingState)
        when(currentTrainingState) {
            TrainingStates.STOPPED -> {
                currentTrainingState = TrainingStates.IN_PROGRESS
                resetTraining()
                trainingView.startTraining()
            }
            TrainingStates.IN_PROGRESS -> {
                currentTrainingState = TrainingStates.PAUSED
                disposableTimer.dispose()
                trainingView.stopLocationTracking()
            }
            TrainingStates.PAUSED -> {
                currentTrainingState = TrainingStates.IN_PROGRESS
                startTimerAndDistanceTracker()
            }
        }
        trainingView.setButtons(currentTrainingState)
    }

    override fun registerView(view: TrainingView) {
        trainingView = view
    }

    override fun initTraining(trainingType: TrainingType) {
        stroredTrainingType = trainingType
        trainingView.chooseTrainignImage(trainingType)
        resetTraining()
        trainingView.setButtons(TrainingStates.STOPPED)
        currentTrainingState = TrainingStates.STOPPED
        trainingView.getCurrentLocation()
    }

    val TAG = "KRZYS"

    override fun startTraining() {
        startTimerAndDistanceTracker()
    }

    private fun resetTraining() {
        trainingTime = 0L
        trainingView.setTime(ClockTime.clockFromMilis(trainingTime))
        trainingView.setDistance(initialTrainingDistance)
    }
}