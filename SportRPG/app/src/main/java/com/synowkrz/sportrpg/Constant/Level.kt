package com.synowkrz.sportrpg.Constant

class Level {

    companion object {
        fun levelUpLimit(level : Int) : Int {
            return level * 1000
        }

        fun checkLevel(experience : Long) : Int {
            var level : Int = 1
            var experienceToNextLevel = 0
            while (true) {
                experienceToNextLevel += levelUpLimit(level)
                if (experience > experienceToNextLevel) {
                    level += 1
                } else {
                    break
                }
            }
            return level
        }


        fun calculateLevelProgress(experience: Long, level: Int) : Long {
            if (level == 1) {
                return experience
            }
            var totalExpernience = 0
            for (i in 1..level - 1) {
                totalExpernience += levelUpLimit(i)
            }
            return experience - totalExpernience
        }
    }
}