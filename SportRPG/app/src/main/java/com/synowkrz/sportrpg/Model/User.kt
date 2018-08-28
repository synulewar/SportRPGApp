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
                var mana: Int,
                var abilityPoints: Int,
                var skillPoints: Int,
                var inventory: String,
                var skills: String,
                var type: Int) {
    @Ignore
    constructor(email: String, name: String, type: Int) : this(email, name, 0.0, 0.0, 0.0,
            0, 1, 0, 1,1,1,1,1,1, 5, 1, "", "", type)

    fun addTrainingResulst(trainingType: TrainingType, time: Long, distance: Double, trainingScore: Long) {
        when (trainingType) {
            TrainingType.WALK -> {walkDis += distance}
            TrainingType.RUN -> {runDis += distance}
            TrainingType.BIKE -> {bikeDis += distance}
        }
        score += trainingScore
        experience += trainingScore
        var previousLevel = level
        level = Level.checkLevel(experience)
        if (level > previousLevel) {
            abilityPoints += 5 * (level - previousLevel)
            skillPoints += 1 * (level - previousLevel)
        }
    }
}