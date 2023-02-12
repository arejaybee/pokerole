package com.arejaybee.pokerole_core_sheet.trainer

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon.PokemonChangeListener
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerKnowledge
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerSurvival
import com.google.gson.Gson
import com.google.gson.annotations.Expose


class Trainer(@Transient private  var context: Context) : BagUpdateListener, PokemonChangeListener {

    companion object {
        const val SHARED_PREFS = "shared_pref"
        const val POKEMON_PER_BOX = 20
        fun loadData(context: Context): Trainer {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            val json = sharedPreferences.getString("myCharacter", "")
            val trainer = Gson().fromJson(json, Trainer::class.java)
            trainer.context = context
            trainer.bag.listener = trainer
            trainer.bag.loadFromJson()
            trainer.pokemon.forEach {
                it.listener = trainer
            }
            trainer.pcPokemon?.forEach {
                it.listener = trainer
            }
            return trainer
        }
    }

    fun saveData() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(this)
        editor.putString("myCharacter", json)
        editor.apply()
    }
    
    fun updateSkills(list: List<Int>, type: SkillDialogType) {
        when(type) {
            TrainerFight -> {
                fight = list[0] 
                brawl = list[1]
                tThrow = list[2]
                evasion = list[3]
                weapons = list[4]
            }
            TrainerSurvival -> {
                survival= list[0]
                alert= list[1]
                atheletic= list[2]
                natural= list[3]
                stealth= list[4]
            }
            TrainerContest -> {
                contest= list[0]
                empathy= list[1]
                etiquette= list[2]
                intimidate= list[3]
                perform= list[4]
            }
            TrainerKnowledge -> {
                knowledge= list[0]
                crafts= list[1]
                lore= list[2]
                medicine= list[3]
                science= list[4]
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

    var badges = mutableListOf("","","","","","","","")
        get() {
            return if(field == null)  {
                field = mutableListOf("","","","","","","","")
                field
            }
            else
                field
        }
        set(value) {
            field = value
            saveData()
        }

    //Card fields
    var name = ""
        set(value) {
            field = value
            saveData()
        }

    var nature = Nature.NONE
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

    var pokemon = mutableListOf(
        Pokemon(this),
        Pokemon(this),
        Pokemon(this),
        Pokemon(this),
        Pokemon(this),
        Pokemon(this)
    )
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

    @Expose
    var bag = Bag(this)
        set(value) {
            field = value
            saveData()
        }

    var pcPokemon: MutableList<Pokemon>? = mutableListOf()

    fun addPcBox() {
        if (pcPokemon == null) {
            pcPokemon = mutableListOf()
        }
        val temp = mutableListOf<Pokemon>()
        for(i in  0 until POKEMON_PER_BOX) {
            temp.add(Pokemon(this))
        }
    }

    fun swapPokemonWithPC(pokemonIndex: Int, pcIndex: Int) {
        val temp = Pokemon(pokemon[pokemonIndex])
        val pc = Pokemon(pcPokemon!![pcIndex])
        pokemon[pokemonIndex] = pc
        pcPokemon!![pcIndex] = temp
        saveData()
    }

    fun swapPcPokemonWithPC(pc1:Int, pc2:Int) {
        val temp = Pokemon(pcPokemon!![pc1])
        val pc = Pokemon(pcPokemon!![pc2])
        pcPokemon!![pc1] = pc
        pcPokemon!![pc2] = temp
        saveData()
    }

    fun getMaxBoxNum() : Int{
        val ownedPokmeon = pcPokemon?.filter {
            it.name != ""
        }?.size
        val boxesNeeded = ((ownedPokmeon?:0) / POKEMON_PER_BOX) + 5
        if(pcPokemon == null) {
            addPcBox()
        }
        while(pcPokemon!!.size/POKEMON_PER_BOX < boxesNeeded) {
            addPcBox()
        }
        return boxesNeeded
    }

    override fun saveBagUpdates() {
        saveData()
    }

    override fun savePokemon() {
        saveData()
    }
}