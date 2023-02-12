package com.arejaybee.pokerole_core_sheet.pokemon

import androidx.compose.runtime.MutableState
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonSurvival

class Pokemon(@Transient var listener: PokemonChangeListener) {

    constructor(pokemon:Pokemon) : this(pokemon.listener) {
        profilePicture = pokemon.profilePicture
        name = pokemon.name
        type1 = pokemon.type1
        type2 = pokemon.type2
        nature = pokemon.nature
        ability = pokemon.ability
        size = pokemon.size
        weight = pokemon.weight
        level = pokemon.level
        exp = pokemon.exp
        happiness = pokemon.happiness
        loyalty = pokemon.loyalty
        disobedience = pokemon.disobedience
        hp = pokemon.hp
        will = pokemon.will
        item = pokemon.item
        status = pokemon.status
        initiative = pokemon.initiative
        accuracy = pokemon.accuracy
        damage = pokemon.damage
        evasion = pokemon.evasion
        defense = pokemon.defense
        specialDefense = pokemon.specialDefense
        moves = mutableListOf()
        moves.addAll(pokemon.moves)
        strength = pokemon.strength
        dexterity = pokemon.dexterity
        vitality = pokemon.vitality
        insight = pokemon.insight
        special = pokemon.special
        tough = pokemon.tough
        cool = pokemon.cool
        beauty = pokemon.beauty
        cute = pokemon.cute
        smart = pokemon.smart
        fight = pokemon.fight
        brawl = pokemon.brawl
        channel = pokemon.channel
        evasion = pokemon.evasion
        clash = pokemon.clash
        survival = pokemon.survival
        alert = pokemon.alert
        atheletic = pokemon.atheletic
        natural = pokemon.natural
        stealth = pokemon.stealth
        contest = pokemon.contest
        allure = pokemon.allure
        etiquette = pokemon.etiquette
        intimidate = pokemon.intimidate
        perform = pokemon.perform
    }
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

    fun updateBattleStats(list: List<MutableState<String>>) {
        hp = try {Integer.parseInt(list[0].value)} catch (e: NumberFormatException) { hp }
        will = try {Integer.parseInt(list[1].value)} catch (e: NumberFormatException) { will }
        item = list[2].value
        //status = Integer.parseInt(list[3].value) - ignore this field because it is a dropdown now
        initiative = try {Integer.parseInt(list[4].value)} catch (e: NumberFormatException) { initiative }
        accuracy = try {Integer.parseInt(list[5].value)} catch (e: NumberFormatException) { accuracy }
        damage = try {Integer.parseInt(list[6].value)} catch (e: NumberFormatException) { damage }
        evasion = try {Integer.parseInt(list[7].value)} catch (e: NumberFormatException) { evasion }
        defense = try {Integer.parseInt(list[8].value)} catch (e: NumberFormatException) { defense }
        specialDefense = try {Integer.parseInt(list[9].value)} catch (e: NumberFormatException) { specialDefense }
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

    var moves = mutableListOf("","","","")
        set(value) {
            field = value
            saveData()
        }

    var hp = 0
        set(value) {
            field = value
            saveData()
        }

    var will = 0
        set(value) {
            field = value
            saveData()
        }

    var item = ""
        set(value) {
            field = value
            saveData()
        }

    var status = PokemonStatus.Healthy
        set(value) {
            field = value
            saveData()
        }

    var initiative = 0
        set(value) {
            field = value
            saveData()
        }

    var accuracy = 0
        set(value) {
            field = value
            saveData()
        }

    var damage = 0
        set(value) {
            field = value
            saveData()
        }

    var defense = 0
        set(value) {
            field = value
            saveData()
        }

    var specialDefense = 0
        set(value) {
            field = value
            saveData()
        }


    interface PokemonChangeListener {
        fun savePokemon()
    }
}