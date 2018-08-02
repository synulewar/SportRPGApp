package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userData")
data class User(@PrimaryKey val name: String, var runDis: Double, var bikeDis: Double, var walkDis: Double, var score: Long) {
    @Ignore
    constructor(name: String) : this(name, 0.0, 0.0, 0.0, 0)
}