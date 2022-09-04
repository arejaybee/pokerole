package com.arejaybee.pokerole_core_sheet.trainer

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.google.gson.Gson


class Trainer(@Transient private  var context: Context) {

    companion object {
        val SHARED_PREFS = "shared_pref"
        fun loadData(context: Context): Trainer {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            val json = sharedPreferences.getString("myCharacter", "")
            val trainer = Gson().fromJson(json, Trainer::class.java)
            trainer.context = context
            return trainer
        }
    }

    private fun saveData() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(this)
        editor.putString("myCharacter", json)
        editor.apply()
    }


    //Card fields
    var name = ""
        set(value) {
            field = value
            saveData()
        }

    var nature = Nature.NATURE
        set(value) {
            field = value
            saveData()
        }

    var age = ""
        set(value) {
            field = value
            saveData()
        }

    var playerName = ""
        set(value) {
            field = value
            saveData()
        }

    var concept: Concept = Concept()
        set(value) {
            field = value
            saveData()
        }

    var curHp = 0
        set(value) {
            field = value
            saveData()
        }

    var maxHp = 0
        set(value) {
            field = value
            saveData()
        }

    var will = 0
        set(value) {
            field = value
            saveData()
        }

    var money = ""
        set(value) {
            field = value
            saveData()
        }

    var experience = 0
        set(value) {
            field = value
            saveData()
        }

    var pokemon = listOf(Pokemon(), Pokemon(), Pokemon(), Pokemon(), Pokemon(), Pokemon())
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

    var intelligence = 0
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

    var tThrow = 0
        set(value) {
            field = value
            saveData()
        }

    var evasion = 0
        set(value) {
            field = value
            saveData()
        }

    var weapons = 0
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

    var empathy = 0
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

    var knowledge = 0
        set(value) {
            field = value
            saveData()
        }

    var crafts = 0
        set(value) {
            field = value
            saveData()
        }

    var lore = 0
        set(value) {
            field = value
            saveData()
        }

    var medicine = 0
        set(value) {
            field = value
            saveData()
        }

    var science = 0
        set(value) {
            field = value
            saveData()
        }

    var otherSkills = ""
        set(value) {
            field = value
            saveData()
        }

    var bag = Bag()
        set(value) {
            field = value
            saveData()
        }
}