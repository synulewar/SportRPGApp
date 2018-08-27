package com.synowkrz.sportrpg.Model

import com.synowkrz.sportrpg.R

class GameResource {
    companion object {

        val RAT = "Rat"
        val FROZEN = "Frozen"
        val SWORD = "Sword"
        val SHIELD = "Shield"


        val monsterList = listOf(RAT)
        val monsterType = listOf(FROZEN)


        val imageResource = mapOf<String, Int>(
                RAT to R.drawable.rat,
                SWORD to R.drawable.sword,
                SHIELD to R.drawable.shield
        )
    }
}