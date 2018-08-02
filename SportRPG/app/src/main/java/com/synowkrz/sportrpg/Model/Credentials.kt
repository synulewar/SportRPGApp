package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "credentialTable")
data class Credentials(@PrimaryKey val email: String, var name: String, var password: String) {
    @Ignore
    constructor(email: String, password: String) : this(email, "", password)

}