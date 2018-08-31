package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.util.Log
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

        fun resolveSkillLevelAndAttribute(skill: Skill, attributeValue: Int) : Skill {
            var resolvedSkill : Skill = skill.copy()
            var resolvedList = mutableListOf<BasicSkill>()
            var basicSkillList = BasicSkill.convertStringIntoBasicSkillList(skill.basicSkillList)
            for (basicSkill in basicSkillList) {
                when(basicSkill.effect) {

                    SecondaryAtributes.DMG,
                    SecondaryAtributes.MAGIC_DMG,
                    SecondaryAtributes.DEF,
                    SecondaryAtributes.MAGIC_DEF,
                    SecondaryAtributes.TRUE_DMG,
                    SecondaryAtributes.HEALTH,
                    SecondaryAtributes.MAGIC_THORNS,
                    SecondaryAtributes.THORNS -> {
                        var value = basicSkill.value *
                                Math.pow(skill.level.toDouble(), skill.levelFactor) +
                                        skill.attributeFactor * attributeValue
                        resolvedList.add(BasicSkill(basicSkill.effect, value.toInt()))
                    }

                    SecondaryAtributes.HIT_CHANCE,
                    SecondaryAtributes.DODGDE_CHANCE,
                    SecondaryAtributes.CRIT_CHANCE,
                    SecondaryAtributes.CRIT_DMG,
                    SecondaryAtributes.STUN -> {


                        var value = basicSkill.value *
                                Math.pow(skill.level.toDouble(), skill.levelFactor) +
                                (skill.attributeFactor - 1) * attributeValue
                        resolvedList.add(BasicSkill(basicSkill.effect, value.toInt()))
                    }


                    SecondaryAtributes.BLIND,
                    SecondaryAtributes.MORALE,
                    SecondaryAtributes.STACK,
                    SecondaryAtributes.BURST_STACK,
                    SecondaryAtributes.INVISIBLE -> {
                        Log.d(BasicSkill.TAG, "Do nothing")
                    }
                }
            }

            resolvedSkill.basicSkillList = BasicSkill.convertItemListIntoJson(resolvedList)
            return resolvedSkill
        }
    }
}