package com.synowkrz.sportrpg.Controller

import android.os.SystemClock
import android.util.Log
import com.synowkrz.sportrpg.Dao.TrainingDao
import com.synowkrz.sportrpg.Model.ClockTime
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
    override fun onStopFinishPressed() {
        when(currentTrainingState) {
            TrainingStates.IN_PROGRESS -> {
                disposableTimer.dispose()
                currentTrainingState = TrainingStates.STOPPED
                trainingView.setButtons(TrainingStates.STOPPED)
            }
        }
    }

    override fun startTimerAndDistanceTracker() {
        trainingStartTimestamp = SystemClock.uptimeMillis()
        Log.d(TAG,  "Subscribe timer rx")
        disposableTimer = timerRX
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    var diff = SystemClock.uptimeMillis() - trainingStartTimestamp
                    trainingView.setTime(ClockTime.clockFromMilis(diff))
                }
    }

    override fun onStartPausePressed() {

        Log.d(TAG, "onStratPausePressed " + currentTrainingState)
        when(currentTrainingState) {
            TrainingStates.STOPPED -> {
                currentTrainingState = TrainingStates.IN_PROGRESS
                resetTraining()
                trainingView.setButtons(TrainingStates.IN_PROGRESS)
                trainingView.startTraining()
            }
        }
    }


    lateinit var trainingView: TrainingView
    lateinit var disposableTimer : Disposable
    val initialTrainingTime = 0L;
    val initialTrainingDistance = 6.1
    var currentTrainingState : TrainingStates = TrainingStates.STOPPED
    var trainingStartTimestamp : Long = 0L
    var timerRX : Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)



    override fun registerView(view: TrainingView) {
        trainingView = view
    }

    override fun initTraining(trainingType: TrainingType) {
        trainingView.chooseTrainignImage(trainingType)
        resetTraining()
        trainingView.setButtons(TrainingStates.STOPPED)
        currentTrainingState = TrainingStates.STOPPED
    }

    val TAG = "KRZYS"

    override fun startTraining() {
        startTimerAndDistanceTracker()
    }

    private fun resetTraining() {
        trainingView.setTime(ClockTime.clockFromMilis(initialTrainingTime))
        trainingView.setDistance(initialTrainingDistance)
    }

}