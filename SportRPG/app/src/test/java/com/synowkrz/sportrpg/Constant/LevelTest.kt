package com.synowkrz.sportrpg.Constant

import org.junit.Assert.*
import org.junit.Test

class LevelTest {

    @Test
    fun levelUpLimitTest() {
        assertEquals(1000, Level.levelUpLimit(1))
        assertEquals(2000, Level.levelUpLimit(2))
        assertEquals(7000, Level.levelUpLimit(7))
    }

    @Test
    fun checkLevel() {
        assertEquals(1, Level.checkLevel(500L))
        assertEquals(4, Level.checkLevel(6100L))
        assertEquals(5, Level.checkLevel(10100L))
        assertEquals(6, Level.checkLevel(19100L))
    }

    @Test
    fun calculateLevelProgressTest() {
        assertEquals(100, Level.calculateLevelProgress(100, 1))
        assertEquals(100, Level.calculateLevelProgress(1100, 2))
        assertEquals(485, Level.calculateLevelProgress(6485, 4))
    }
}