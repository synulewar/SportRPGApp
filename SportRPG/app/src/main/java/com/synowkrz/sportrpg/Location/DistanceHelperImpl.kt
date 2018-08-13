package com.synowkrz.sportrpg.Location

import android.location.Location
import android.util.Log

class DistanceHelperImpl : DistanceHelper {


    override fun getTotalDsitance(): Double {
        return overalDistance
    }

    companion object {
        fun getInstance() : DistanceHelper = DistanceHelperImpl()
    }

    var locationList : MutableList<Location> = mutableListOf()
    var overalDistance : Double = 0.0
    var TAG = "KRZYS"
    lateinit var lastLocation: Location

    override fun clearLocationList() {
        locationList.clear()
    }

    override fun addNewLocation(location: Location) {
        locationList.add(location)
        calculateTravelledDistance()
    }


    override fun setLastKnownLocation(location : Location) {
        lastLocation = location
    }

    private fun calculateTravelledDistance() {
        var size = locationList.size
        if (size > 2) {
            var latestLocation = locationList.get(size - 1)
            var previousLocation = locationList.get(size -2)
            var distance = latestLocation.distanceTo(previousLocation)
            if (distance >= 0) {
                overalDistance += distance
            }
            Log.d(TAG, "Latest distance " + distance + " total distance " + overalDistance)
        }
    }
}