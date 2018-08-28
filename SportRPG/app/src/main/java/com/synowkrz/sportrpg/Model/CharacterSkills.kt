package com.synowkrz.sportrpg.Model

class CharacterSkills {

    companion object {

        val fireDmage = BasicSkill(SecondaryAtributes.MAGIC_DMG, 10)
        val fireStack = BasicSkill(SecondaryAtributes.STACK, 1)
        val fireBallList = listOf(fireDmage, fireStack)
        val FIRE_BALL = Skill("Fire Ball",
                "Simple fire attack, adding one stack",
                BasicSkill.convertItemListIntoJson(fireBallList),
                1.2,
                1,
                1.1,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                1,
                SkillType.A0,
                1
                )



        val coldDmage = BasicSkill(SecondaryAtributes.MAGIC_DMG, 6)
        val coldStun = BasicSkill(SecondaryAtributes.STUN, 10)
        val coldStack = BasicSkill(SecondaryAtributes.STACK, 2)
        val coldShotList = listOf(coldDmage, coldStun, coldStack)
        val COLD_SHOT = Skill("Cold shot",
                "Ice demage with chance to stun opponent, adding 2 stacks",
                BasicSkill.convertItemListIntoJson(coldShotList),
                1.3,
                0,
                1.05,
                BasicAttributes.SPELL_POWER,
                SkillOccurrence.SINGLE,
                1,
                2,
                SkillType.A1,
                5
                )


        val curseDmg = BasicSkill(SecondaryAtributes.MAGIC_DMG, 2)
        val curseHitChance = BasicSkill(SecondaryAtributes.HIT_CHANCE, -10)
        val curseMagicResist = BasicSkill(SecondaryAtributes.MAGIC_DEF, -25)
        val curseArmor = BasicSkill(SecondaryAtributes.DEF, -25)
        val curseList = listOf(curseDmg, curseHitChance, curseMagicResist, curseArmor)




    }



}