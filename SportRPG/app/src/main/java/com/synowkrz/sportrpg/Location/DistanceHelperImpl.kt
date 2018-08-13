package com.synowkrz.sportrpg.Location

import android.location.Location

class DistanceHelperImpl : DistanceHelper {

    var locationList : MutableList<Location> = mutableListOf()
    lateinit var lastLocation: Location

    override fun clearLocationList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewLocation(location: Location) {
        locationList.add(location)
    }

    override fun getOveralDistance() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setLastKnownLocation(location : Location) {
        lastLocation = location
    }
}