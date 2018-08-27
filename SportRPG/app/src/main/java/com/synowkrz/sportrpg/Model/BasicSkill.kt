package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class BasicSkill(var effect: Int,
                      var value: Int
) {
    companion object {
        fun convertStringIntoBasicSkillList(skillString: String) : List<BasicSkill> {
            val gson = Gson()
            val basicSkillList : List<BasicSkill> = gson.fromJson(skillString, object : TypeToken<List<BasicSkill>>() {}.type)
            return basicSkillList
        }

        fun convertItemListIntoJson(basicSkillList: List<BasicSkill>) : String {
            val gson = Gson()
            return gson.toJson(basicSkillList)
        }
    }
}