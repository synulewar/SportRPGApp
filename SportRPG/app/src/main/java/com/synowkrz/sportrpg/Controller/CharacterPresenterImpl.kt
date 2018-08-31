package com.synowkrz.sportrpg.Controller

import android.util.Log
import android.view.View
import com.synowkrz.sportrpg.Dao.UserDao
import com.synowkrz.sportrpg.Model.*
import com.synowkrz.sportrpg.View.Character.CharacterView
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenterImpl @Inject constructor(var userDao: UserDao) : CharacterPresenter {

    override fun onSkillChange(skillType: SkillType) {
        if (mainUser.skillPoints > 0) {
            mainUser.skillPoints -= 1
            var skillList = getUserSkills()
            for (skill in skillList) {
                if (skill.skillType == skillType) {
                    skill.level += 1
                }
            }
            var skillString = Skill.convertItemListIntoJson(skillList)
            mainUser.skills = skillString
            userDao.insert(mainUser)
            refreshSkillView()
        }
    }

    private fun refreshSkillView() {
        var visbilityMap: Map<SkillType, Int> = createSkillVisbilityMap()
        characterView.bindSkillFragmentData(Skill.convertStringIntoSkillList(mainUser.skills), visbilityMap, mainUser.skillPoints)
    }


    companion object {
        val VISIBLE = View.VISIBLE
        val INVISBILE = View.INVISIBLE
        val MOCK_USER_EMAIL = "mock@gmail.com"
        val MOCK_USER_NAME = "mockName"
        val MOCK_TYPE = 0
    }

    val TAG = "KRZYS"
    lateinit var mainUser : User
    lateinit var characterView: CharacterView
    var lastConfirmedUser : User = User(MOCK_USER_EMAIL, MOCK_USER_NAME, MOCK_TYPE)

    override fun loadUserData(email: String) {
        var maybeUser = Maybe.create<User> { emitter ->
            var user = userDao.getUser(email)
            if (user != null) {
                Log.d(TAG, "KRZYS onSuccess called")
                mainUser = user
                emitter.onSuccess(user)
            } else {
                emitter.onComplete()
            }
        }

        maybeUser
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ user ->
                    characterView.bindUserWithView(user)
                    characterView.setAllButtonsVisibility(INVISBILE)
                    if (mainUser.abilityPoints != 0) {
                        characterView.setIncreaseButtonsVisibility(VISIBLE)
                    }
                 }
    }

    override fun registerView(view: CharacterView) {
        characterView = view
    }

    override fun onConfirmButtonPressed() {
        userDao.insert(mainUser)
        lastConfirmedUser = User(MOCK_USER_EMAIL, MOCK_USER_NAME, MOCK_TYPE)
        characterView.setAllDecreaseButtonsVisibility(INVISBILE)
        characterView.setConfirmButtonVisibility(INVISBILE)
    }

    override fun onAbilityChange(type: BasicAttributes, increase: Boolean) {
        if (lastConfirmedUser.email.equals(MOCK_USER_EMAIL)) {
            lastConfirmedUser = mainUser.copy()
        }

        when(type) {
            BasicAttributes.STRENGTH -> if (increase) mainUser.strength += 1 else mainUser.strength -= 1
            BasicAttributes.AGILITY -> if (increase) mainUser.agility += 1 else mainUser.agility -= 1
            BasicAttributes.SPELL_POWER -> if (increase) mainUser.spellPower += 1 else mainUser.spellPower -= 1
            BasicAttributes.VITALITY -> if (increase) mainUser.vitality += 1 else mainUser.vitality -= 1
        }
        if (increase) mainUser.abilityPoints -= 1 else mainUser.abilityPoints += 1
        characterView.bindUserWithView(mainUser)
        resolveAbilityButtonsVisibility()
    }

    override fun onSkillFragmentCreated() {
        refreshSkillView()
    }

    override fun onSkillPictureClicked(skillType: SkillType) {
        var skill = getSkillByType(skillType)
        var abiityValue = 0
        when(skill.affectingAtribute) {
            BasicAttributes.STRENGTH -> abiityValue = mainUser.strength
            BasicAttributes.AGILITY -> abiityValue = mainUser.agility
            BasicAttributes.SPELL_POWER -> abiityValue = mainUser.spellPower
            BasicAttributes.VITALITY -> abiityValue = mainUser.vitality
        }
        characterView.showDetailDialog(abiityValue, skill)
    }

    private fun getUserSkills(): List<Skill> {
        return Skill.convertStringIntoSkillList(mainUser.skills)
    }

    private fun getSkillByType(type: SkillType): Skill {
        var skillList = getUserSkills()
        var resutSkill = CharacterSkills.TEST_SKILL
        for (skill in skillList) {
            if (skill.skillType.equals(type)) {
                resutSkill = skill
                break
            }
        }
        return resutSkill
    }


    private fun createSkillVisbilityMap(): Map<SkillType, Int> {
        var map = mutableMapOf<SkillType, Int>()
        for (type in SkillType.values()) {
            var condition = mainUser.skillPoints > 0
                    && mainUser.level >= Skill.skillLevelLimit.getOrDefault(type, 0)
            map.put(type, if (condition) View.VISIBLE else View.INVISIBLE)
        }
        return map
    }

    private fun resolveAbilityButtonsVisibility() {
        var visible : Int
        if (mainUser.abilityPoints == 0) visible = INVISBILE else visible = VISIBLE
        characterView.setIncreaseButtonsVisibility(visible)

        if (mainUser.strength == lastConfirmedUser.strength) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, BasicAttributes.STRENGTH)

        if (mainUser.agility == lastConfirmedUser.agility) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, BasicAttributes.AGILITY)

        if (mainUser.spellPower == lastConfirmedUser.spellPower) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, BasicAttributes.SPELL_POWER)

        if (mainUser.vitality == lastConfirmedUser.vitality) visible = INVISBILE else visible = VISIBLE
        characterView.setDecreaseButtonsVisibility(visible, BasicAttributes.VITALITY)

        if (mainUser.equals(lastConfirmedUser))  visible = INVISBILE else visible = VISIBLE
        characterView.setConfirmButtonVisibility(visible)
    }
}