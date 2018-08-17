package com.synowkrz.sportrpg.inventory

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "itemsTable")
data class Item(@PrimaryKey val name:String,
                val description: String,
                val cost: Int,
                val primaryParameter: Int,
                val primaryParameterValue: Int,
                val secondaryParameter: Int,
                val secondaryParameterValue: Int,
                val itemType: Int) {
}