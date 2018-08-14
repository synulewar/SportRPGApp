package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.synowkrz.sportrpg.Constant.Level

@Entity(tableName = "userData")
data class User(@PrimaryKey val email: String,
                val name: String,
                var runDis: Double,
                var bikeDis: Double,
                var walkDis: Double,
                var score: Long,
                var level : Int,
                var experience: Long,
                var strength: Int,
                var agility: Int,
                var spellPower: Int,
                var vitality: Int,
                var hp: Int,
                var mana: Int) {
    @Ignore
    constructor(email: String, name: String) : this(email, name, 0.0, 0.0, 0.0,
            0, 1, 0, 1,1,1,1,1,1)

    fun addTrainingResulst(trainingType: TrainingType, time: Long, distance: Double, trainingScore: Long) {
        when (trainingType) {
            TrainingType.WALK -> {walkDis += distance}
            TrainingType.RUN -> {runDis += distance}
            TrainingType.BIKE -> {bikeDis += distance}
        }
        score += trainingScore
        experience += trainingScore
        level = Level.checkLevel(experience)
    }
}