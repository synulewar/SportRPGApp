package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.util.Log
import java.util.concurrent.TimeUnit


@Entity(tableName = "trainingTable")
data class Training(@PrimaryKey(autoGenerate = true) val id: Long, val email: String, val type: Int) {
    companion object {
        val typeScoreMap = hashMapOf(
                TrainingType.WALK to 3,
                TrainingType.RUN to 5,
                TrainingType.BIKE to 1
        )
        val BIKE_SPEED_LIMIT : Double = 40.0
        val RUN_SPEED_LIMIT : Double = 20.0
        val WALK_SPEED_LIMIT : Double = 6.00
        val TAG = "KRZYS"
    }

    var scoreMultiplayer = 250.0
    var distance: Double = 0.0
    var score : Int = 0
    var time : Long = 0L


    fun calcResults() {
        var trainingType = TrainingType.values()[type]
        var speed = calcSpeed()
        Log.d(TAG, "calcResult: " + trainingType.toString() + " speed " + calcSpeed())
        var factor : Int = establishFactor(trainingType, speed)
        var score_tmp = distance * scoreMultiplayer * factor
        score = score_tmp.toInt()
        Log.d(TAG, "calcResult: After verifiction factor " + factor + " score " + score)
    }


    private fun calcSpeed() : Double {
        if (time != 0L) {
            var hours = TimeUnit.MILLISECONDS.toHours(time)
            var speed = distance/hours
            Log.d(TAG, "calcSpeed: time " + time + " distance " + distance + " speed " + speed + " km")
            return speed
        }
        return 1.0
    }

    private fun establishFactor(trainingType: TrainingType, speed: Double): Int {
        var factor : Int? = 0
        when (trainingType) {
            TrainingType.BIKE -> {
                if (speed < BIKE_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.BIKE)
                }
            }

            TrainingType.RUN -> {
                if (RUN_SPEED_LIMIT < speed && speed < BIKE_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.BIKE)
                } else if (speed > WALK_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.RUN)
                }
            }

            TrainingType.WALK -> {
                if (speed < WALK_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.WALK)
                } else if (speed < RUN_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.RUN)
                } else if (speed < BIKE_SPEED_LIMIT) {
                    factor = typeScoreMap.get(TrainingType.BIKE)
                }
            }
        }
        if (factor == null) {
            factor = 0
        }
        return factor
    }

}