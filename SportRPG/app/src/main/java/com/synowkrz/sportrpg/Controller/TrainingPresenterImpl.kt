package com.synowkrz.sportrpg.Controller

import android.util.Log
import com.synowkrz.sportrpg.Dao.TrainingDao
import javax.inject.Inject

class TrainingPresenterImpl @Inject constructor(trainingDao: TrainingDao) : TrainingPresenter {

    val TAG = "KRZYS"

    override fun startTraining() {
        Log.d(TAG, "Start training")
    }

}