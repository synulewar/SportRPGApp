package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userData")
data class User(@PrimaryKey val email: String, val name: String, var runDis: Double, var bikeDis: Double, var walkDis: Double, var score: Long, var level : Int, var experience: Int) {
    @Ignore
    constructor(email: String, name: String) : this(email, name, 0.0, 0.0, 0.0, 0, 1, 0)
}