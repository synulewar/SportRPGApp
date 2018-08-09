package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "trainingTable")
data class Training(@PrimaryKey(autoGenerate = true) val id : Long, val email: String, val type: Int) {
    var distance: Double = 0.0
}