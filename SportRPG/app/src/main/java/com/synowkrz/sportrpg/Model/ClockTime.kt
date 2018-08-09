package com.synowkrz.sportrpg.Model

data class ClockTime(val hours : Long, val minutes: Long, val seconds: Long) {
    companion object {
        val miliInHours = 3600000
        val miliInMinutes = 60000
        val miliInSeconds = 1000

        fun clockFromMilis(miliseconds : Long) : ClockTime {
            var hours = miliseconds / miliInHours
            var minutes = miliseconds % miliInHours / miliInMinutes
            var seconds = miliseconds % miliInHours % miliInMinutes / miliInSeconds
            return ClockTime(hours, minutes, seconds)
        }
    }

}