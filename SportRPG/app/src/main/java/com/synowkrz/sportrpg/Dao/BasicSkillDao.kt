package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.BasicSkill


@Dao
interface BasicSkillDao {
    @Query("SELECT * from basicSkillTable")
    fun getAllBasicSkills() : List<BasicSkill>

    @Query("SELECT * from basicSkillTable WHERE name = :name")
    fun getBasicSkill(name: String) : BasicSkill

    @Insert(onConflict = REPLACE)
    fun insertBasicSkill(basicSkill: BasicSkill)
}