package com.synowkrz.sportrpg.Location

import android.location.Location

interface DistanceHelper {
    fun setLastKnownLocation(location : Location)
    fun clearLocationList()
    fun addNewLocation(location: Location)
    fun getOveralDistance()
}