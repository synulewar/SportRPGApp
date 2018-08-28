package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "skillTable")
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
                 var skillType: SkillType,
                 var levelLimit: Int
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
    }
}