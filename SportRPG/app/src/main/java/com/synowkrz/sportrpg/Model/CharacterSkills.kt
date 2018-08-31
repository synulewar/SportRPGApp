package com.synowkrz.sportrpg.Model

import com.synowkrz.sportrpg.R

class CharacterSkills {

    companion object {
        val fireBallName = "Fire ball"

        val fireDmage = BasicSkill(SecondaryAtributes.MAGIC_DMG, 10)
        val fireStack = BasicSkill(SecondaryAtributes.STACK, 1)
        val fireBallList = listOf(fireDmage, fireStack)

        val TEST_SKILL = Skill(fireBallName ,
                "Simple fire attack, adding one stack",
                BasicSkill.convertItemListIntoJson(fireBallList),
                1.2,
                0,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                1,
                SkillType.A0
        )

        val FIRE_BALL = Skill(fireBallName ,
                "Simple fire attack, adding one stack",
                BasicSkill.convertItemListIntoJson(fireBallList),
                1.2,
                0,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                1,
                SkillType.A0
                )


        val coldShotName = "Cold shot"

        val coldDmage = BasicSkill(SecondaryAtributes.MAGIC_DMG, 6)
        val coldStun = BasicSkill(SecondaryAtributes.STUN, 10)
        val coldStack = BasicSkill(SecondaryAtributes.STACK, 2)
        val coldShotList = listOf(coldDmage, coldStun, coldStack)
        val COLD_SHOT = Skill(coldShotName,
                "Ice demage with chance to stun opponent, adding 2 stacks",
                BasicSkill.convertItemListIntoJson(coldShotList),
                1.3,
                0,
                1.05,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                2,
                SkillType.A1
                )



        val curseName = "Curse"
        val curseDmg = BasicSkill(SecondaryAtributes.MAGIC_DMG, 2)
        val curseHitChance = BasicSkill(SecondaryAtributes.HIT_CHANCE, -10)
        val curseMagicResist = BasicSkill(SecondaryAtributes.MAGIC_DEF, -25)
        val curseArmor = BasicSkill(SecondaryAtributes.DEF, -25)
        val curseList = listOf(curseDmg, curseHitChance, curseMagicResist, curseArmor)
        val CURSE = Skill(curseName, "Cursed opponent lower it s defense and chance to hit",
                BasicSkill.convertItemListIntoJson(curseList),
                1.1,
                0,
                1.05,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.PERMANENT,
                5,
                5,
                SkillType.A2
                )

        val daggerName = "Dagger rain"
        val daggerTrueDmg = BasicSkill(SecondaryAtributes.TRUE_DMG, 10)
        val daggerMagicDamage = BasicSkill(SecondaryAtributes.TRUE_DMG, 10)
        val daggerList = listOf(daggerTrueDmg, daggerMagicDamage)
        val DAGGER_RAIN = Skill(daggerName,
                "Creates rain of daggers causing true and magic DMG",
                BasicSkill.convertItemListIntoJson(daggerList),
                1.05,
                0,
                1.01,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                2,
                SkillType.A3)


        val overloadName = "Magic overload"
        val overloadDmg = BasicSkill(SecondaryAtributes.DMG, 20)
        val overloadStackBurst = BasicSkill(SecondaryAtributes.BURST_STACK, 5)
        val overloadList = listOf(overloadDmg, overloadStackBurst)
        val MAGIC_OVERLOAD = Skill(overloadName,
                "Burst magic stacks causing significant magic damage",
                BasicSkill.convertItemListIntoJson(overloadList),
                1.1,
                0,
                1.02,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                10,
                SkillType.A4)



        val plantName = "Plants heal"
        val plantsHeal = BasicSkill(SecondaryAtributes.HEALTH, 10)
        val plantsList = listOf(plantsHeal)
        val PLANTS_HEAL = Skill(plantName,
                "Heal your hero",
                BasicSkill.convertItemListIntoJson(plantsList),
                1.1,
                0,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                2,
                SkillType.D0)


        val frozenShieldName = "Frozen shield"
        val frozenShieldDef = BasicSkill(SecondaryAtributes.DEF, 20)
        val frozenShieldMagicDef = BasicSkill(SecondaryAtributes.MAGIC_DEF, 20)
        val forozenList = listOf(frozenShieldDef, frozenShieldMagicDef)
        val FROZEN_SHIELD = Skill(frozenShieldName,
                "Improve hero defense",
                BasicSkill.convertItemListIntoJson(forozenList),
                1.1,
                0,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.PERMANENT,
                3,
                3,
                SkillType.D1)


        val regenerationName = "Regeneration"
        val regenerationHeal =  BasicSkill(SecondaryAtributes.HEALTH, 5)
        val regenerationList = listOf(regenerationHeal)
        val REGENERATION = Skill(regenerationName,
                "Heal hero every turn",
                BasicSkill.convertItemListIntoJson(regenerationList),
                1.2,
                0,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.EVERY_TURN,
                5,
                5,
                SkillType.D2)

        val firewallName = "Fire wall"
        val fireThorns = BasicSkill(SecondaryAtributes.MAGIC_THORNS, 10)
        val fireShieldDef = BasicSkill(SecondaryAtributes.DEF, 10)
        val fireShieldMagicDef = BasicSkill(SecondaryAtributes.MAGIC_DEF, 10)
        val fireWallList = listOf(fireThorns, fireShieldDef, fireShieldMagicDef)
        val FIRE_WALL = Skill(firewallName,
                "Creates wall of fire around hero casuing damage to attacker and improving defense",
                BasicSkill.convertItemListIntoJson(fireWallList),
                1.1,
                0,
                1.01,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.PERMANENT,
                5,
                10,
                SkillType.D3)


        val invisibleName = "Invisible"
        val ivisibleStance = BasicSkill(SecondaryAtributes.INVISIBLE, 1)
        val invisibleList = listOf(ivisibleStance)
        val INVISIBILITY = Skill(invisibleName,
                "Make your hero invisible. Ennemy can not attack you",
                BasicSkill.convertItemListIntoJson(invisibleList),
                1.5,
                0,
                1.0,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.PERMANENT,
                3,
                10,
                SkillType.D4
        )

        val initalMageSkills = listOf(
                FIRE_BALL,
                COLD_SHOT,
                CURSE,
                DAGGER_RAIN,
                MAGIC_OVERLOAD,
                PLANTS_HEAL,
                FROZEN_SHIELD,
                REGENERATION,
                FIRE_WALL,
                INVISIBILITY
                )

        val mageSkillMap = mapOf(
                fireBallName to FIRE_BALL,
                coldShotName to COLD_SHOT,
                curseName to CURSE,
                daggerName to DAGGER_RAIN,
                overloadName to MAGIC_OVERLOAD,
                plantName to PLANTS_HEAL,
                frozenShieldName to FROZEN_SHIELD,
                regenerationName to REGENERATION,
                firewallName to FIRE_WALL,
                invisibleName to INVISIBILITY)


        val mageSkillNamesDrawable = mapOf(
                fireBallName to R.drawable.magic_ball,
                coldShotName to R.drawable.ice_shot,
                curseName to R.drawable.curse,
                daggerName to R.drawable.dagger_rain,
                overloadName to R.drawable.magic_overpower,
                plantName to R.drawable.healing_plants,
                frozenShieldName to R.drawable.frozen_shield,
                regenerationName to R.drawable.regeneration,
                firewallName to R.drawable.fire_wall,
                invisibleName to R.drawable.invisible
                )

        fun getInitalMageSkills() : String {
            return Skill.convertItemListIntoJson(initalMageSkills)
        }

        fun getSkillDrawable(key: String) : Int {
            return mageSkillNamesDrawable.getOrDefault(key, R.drawable.sword)
        }
    }


}