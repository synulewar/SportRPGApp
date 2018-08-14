package com.synowkrz.sportrpg.View

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.*
import com.synowkrz.sportrpg.Constant.ContractValues
import com.synowkrz.sportrpg.Controller.TrainingPresenter
import com.synowkrz.sportrpg.DaggerComponents.SportRPGApp
import com.synowkrz.sportrpg.Model.ClockTime
import com.synowkrz.sportrpg.Model.Training
import com.synowkrz.sportrpg.Model.TrainingStates
import com.synowkrz.sportrpg.Model.TrainingType
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.activity_training.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import javax.inject.Inject

class TrainingActivity : AppCompatActivity(), TrainingView {

    val TAG = "KRZYS"
    val UPDATE_INTERVAL = 6000L

    @Inject
    lateinit var trainingPresenter: TrainingPresenter

    @Inject
    lateinit var fusedLocationClient : FusedLocationProviderClient

    private lateinit var locationCallback: LocationCallback


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

    override fun displayFinalResults(training : Training) {
        var msg = "Trainign results type " + training.type + " dist " + training.distance + " score " + training.score
        longToast(msg)
        Log.d(TAG, msg)
        var intent = Intent()
        intent.putExtra(ContractValues.DISTANCE_KEY, training.distance)
                .putExtra(ContractValues.TIME_KEY, training.time)
                .putExtra(ContractValues.TYPE_KEY, training.type)
                .putExtra(ContractValues.SCORE_KEY, training.score)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

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
        distanceValue.text = "%.2f km".format(distance)
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

    override fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener {location: Location? ->
                if (location != null) {
                    var msg = "Start Location longitude " + location.longitude + " latitude " + location.latitude
                    toast(msg)
                    Log.d(TAG, msg)
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    override fun startLocationTracking() {
        Log.d(TAG, "Tracking distance started")
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = UPDATE_INTERVAL
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null) {
                    var location = locationResult.lastLocation
                    var msg = "Location changed longitude " + location.longitude + " latitude " + location.latitude
                    toast(msg)
                    Log.d(TAG, msg)
                    trainingPresenter.onLocationChanged(location)
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }


    override fun stopLocationTracking() {
        Log.d(TAG, "Stop location tracking")
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun initDagger() {
        (application as SportRPGApp).getAppComponent().inject(this)
    }
}