package com.synowkrz.sportrpg.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.synowkrz.sportrpg.R

data class BasicSkill(var effect: SecondaryAtributes,
                      var value: Int
) {
    companion object {

        val TAG = "KRZYS"

        fun convertStringIntoBasicSkillList(skillString: String) : List<BasicSkill> {
            val gson = Gson()
            val basicSkillList : List<BasicSkill> = gson.fromJson(skillString, object : TypeToken<List<BasicSkill>>() {}.type)
            return basicSkillList
        }

        fun convertItemListIntoJson(basicSkillList: List<BasicSkill>) : String {
            val gson = Gson()
            return gson.toJson(basicSkillList)
        }

        fun getSecondaryAttributeName(secondaryAtribute: SecondaryAtributes) : Int {
            when(secondaryAtribute) {
                SecondaryAtributes.DMG -> return R.string.dmg_alas
                SecondaryAtributes.MAGIC_DMG ->  return R.string.magic_dmg_alias
                SecondaryAtributes.DEF -> return R.string.defense_alias
                SecondaryAtributes.MAGIC_DEF -> return R.string.magic_defense_alias
                SecondaryAtributes.HIT_CHANCE -> return R.string.hit_chance_alias
                SecondaryAtributes.DODGDE_CHANCE -> return R.string.dodge_chance_alias
                SecondaryAtributes.CRIT_CHANCE -> return R.string.crit_chance_alias
                SecondaryAtributes.CRIT_DMG -> return R.string.crit_dmg_alias
                SecondaryAtributes.STUN -> return R.string.stun_alias
                SecondaryAtributes.BLIND -> return R.string.blind_alias
                SecondaryAtributes.MORALE -> return R.string.morale_alias
                SecondaryAtributes.STACK -> return R.string.stack_alias
                SecondaryAtributes.BURST_STACK -> return R.string.burst_stack_alias
                SecondaryAtributes.TRUE_DMG -> return R.string.true_dmg_alias
                SecondaryAtributes.HEALTH -> return R.string.health_alias
                SecondaryAtributes.MAGIC_THORNS -> return R.string.magic_thorns_alias
                SecondaryAtributes.THORNS -> return R.string.defense_alias
                SecondaryAtributes.INVISIBLE -> return R.string.defense_alias
            }
        }

    }
}