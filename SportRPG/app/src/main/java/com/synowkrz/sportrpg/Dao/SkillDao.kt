package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.Skill

interface SkillDao {
    @Query("SELECT * from skillTable")
    fun getAllSkills() : List<Skill>

    @Query("SELECT * from skillTable WHERE name = :name")
    fun getSkill(name: String) : Skill

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSkill(basicSkill: Skill)
}