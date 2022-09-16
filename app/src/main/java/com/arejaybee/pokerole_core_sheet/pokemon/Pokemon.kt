package com.arejaybee.pokerole_core_sheet.pokemon

import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonSurvival

class Pokemon(@Transient var listener: PokemonChangeListener) {


    private fun saveData() {
        listener.savePokemon()
    }

    fun updateSkills(list: List<Int>, type: SkillDialogType) {
        when(type) {
            PokemonFight -> {
                fight = list[0]
                brawl = list[1]
                channel = list[2]
                evasion = list[3]
                clash = list[4]
            }
            PokemonSurvival -> {
                survival= list[0]
                alert= list[1]
                atheletic= list[2]
                natural= list[3]
                stealth= list[4]
            }
            PokemonContest -> {
                contest= list[0]
                allure= list[1]
                etiquette= list[2]
                intimidate= list[3]
                perform= list[4]
            }
            else -> {

            }
        }
    }

    var profilePicture: String = ""
        set(value) {
            field = value
            saveData()
        }

    //Details fields
    var name = ""
        set(value) {
            field = value
            saveData()
        }

    var type1 = PokemonType.None
        set(value) {
            field = value
            saveData()
        }

    var type2 = PokemonType.None
        set(value) {
            field = value
            saveData()
        }

    var nature = Nature.NONE
        set(value) {
            field = value
            saveData()
        }

    var ability = ""
        set(value) {
            field = value
            saveData()
        }
    var size = ""
        set(value) {
            field = value
            saveData()
        }
    var weight = ""
        set(value) {
            field = value
            saveData()
        }

    var level = 0
        set(value) {
            field = value
            saveData()
        }

    var exp = 0
        set(value) {
            field = value
            saveData()
        }

    var happiness = 0
        set(value) {
            field = value
            saveData()
        }

    var loyalty = 0
        set(value) {
            field = value
            saveData()
        }

    var disobedience = 0
        set(value) {
            field = value
            saveData()
        }

    //Skills fields
    var strength = 0
        set(value) {
            field = value
            saveData()
        }

    var dexterity = 0
        set(value) {
            field = value
            saveData()
        }

    var vitality = 0
        set(value) {
            field = value
            saveData()
        }

    var special = 0
        set(value) {
            field = value
            saveData()
        }

    var insight = 0
        set(value) {
            field = value
            saveData()
        }

    var tough = 0
        set(value) {
            field = value
            saveData()
        }

    var cool = 0
        set(value) {
            field = value
            saveData()
        }

    var beauty = 0
        set(value) {
            field = value
            saveData()
        }

    var cute = 0
        set(value) {
            field = value
            saveData()
        }

    var smart = 0
        set(value) {
            field = value
            saveData()
        }

    var fight = 0
        set(value) {
            field = value
            saveData()
        }

    var brawl = 0
        set(value) {
            field = value
            saveData()
        }

    var channel = 0
        set(value) {
            field = value
            saveData()
        }

    var evasion = 0
        set(value) {
            field = value
            saveData()
        }

    var clash = 0
        set(value) {
            field = value
            saveData()
        }

    var survival = 0
        set(value) {
            field = value
            saveData()
        }

    var alert = 0
        set(value) {
            field = value
            saveData()
        }

    var atheletic = 0
        set(value) {
            field = value
            saveData()
        }

    var natural = 0
        set(value) {
            field = value
            saveData()
        }

    var stealth = 0
        set(value) {
            field = value
            saveData()
        }

    var contest = 0
        set(value) {
            field = value
            saveData()
        }

    var allure = 0
        set(value) {
            field = value
            saveData()
        }

    var etiquette = 0
        set(value) {
            field = value
            saveData()
        }

    var intimidate = 0
        set(value) {
            field = value
            saveData()
        }

    var perform = 0
        set(value) {
            field = value
            saveData()
        }

    interface PokemonChangeListener {
        fun savePokemon()
    }
}