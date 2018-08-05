package com.synowkrz.sportrpg.Constant

class Level {

    companion object {
        fun levelUpLimit(level : Int) : Int {
            return level * 1000
        }
    }
}