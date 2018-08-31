package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Skill(@PrimaryKey val name: String,
                 val description: String,
                 var basicSkillList: String,
                 var levelFactor: Double,
                 var level: Int,
                 var attributeFactor: Double,
                 var affectingAtribute: BasicAttributes,
                 var occurrence: SkillOccurrence,
                 var duration: Int,
                 var refresh: Int,
                 var skillType: SkillType
) {

    companion object {
        fun convertStringIntoSkillList(skillString: String) : List<Skill> {
            val gson = Gson()
            val skillList : List<Skill> = gson.fromJson(skillString, object : TypeToken<List<Skill>>() {}.type)
            return skillList
        }

        fun convertItemListIntoJson(skillList: List<Skill>) : String {
            val gson = Gson()
            return gson.toJson(skillList)
        }

        val skillLevelLimit = mapOf(
                SkillType.A0 to 0,
                SkillType.D0 to 0,
                SkillType.A1 to 5,
                SkillType.D1 to 5,
                SkillType.A2 to 10,
                SkillType.D2 to 10,
                SkillType.A3 to 15,
                SkillType.D3 to 15,
                SkillType.A4 to 20,
                SkillType.D4 to 20
        )
    }
}