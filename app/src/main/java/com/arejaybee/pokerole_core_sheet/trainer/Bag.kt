package com.arejaybee.pokerole_core_sheet.trainer

import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Hyper_Potion
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Super_Potion
import com.arejaybee.pokerole_core_sheet.trainer.Potion.PotionListener
import com.google.gson.annotations.Expose

enum class POTION_ENUM {Potion, Super_Potion, Hyper_Potion}
class Bag(@Transient var listener: BagUpdateListener) : PotionListener {
    val badges = mutableListOf<Badge>()
    val battleItems = arrayListOf("","","","")
    val mainItems = arrayListOf("", "", "", "", "", "", "", "")
    val mainItems2 = arrayListOf("", "", "", "", "", "", "", "")
    @Expose
    private val potionList = mutableListOf<Potion>()
    @Expose
    private val superPotionList = mutableListOf<Potion>()
    @Expose
    private val hyperPotionList = mutableListOf<Potion>()

    fun loadFromJson() {
        potionList.forEach {
            it.listener = this
        }
        superPotionList.forEach {
            it.listener = this
        }
        hyperPotionList.forEach {
            it.listener = this
        }
    }

    fun addPotion(type: POTION_ENUM) {
        when(type) {
            POTION_ENUM.Potion -> potionList.add(Potion.getPotionByType(this, type))
            Super_Potion -> superPotionList.add(Potion.getPotionByType(this, type))
            Hyper_Potion -> hyperPotionList.add(Potion.getPotionByType(this, type))
        }
        listener.saveBagUpdates()
    }

    fun popFirstPotion(type: POTION_ENUM) {
        when(type) {
            POTION_ENUM.Potion -> potionList.removeFirst()
            Super_Potion -> superPotionList.removeFirst()
            Hyper_Potion -> hyperPotionList.removeFirst()
        }
        listener.saveBagUpdates()
    }

    fun clearPotions(type: POTION_ENUM) {
        when(type) {
            POTION_ENUM.Potion -> potionList.clear()
            Super_Potion -> superPotionList.clear()
            Hyper_Potion -> hyperPotionList.clear()
        }
        listener.saveBagUpdates()
    }

    fun getPotionList(type: POTION_ENUM) : MutableList<Potion> {
        return when(type) {
            POTION_ENUM.Potion -> potionList
            Super_Potion -> superPotionList
            Hyper_Potion -> hyperPotionList
        }
    }

    override fun savePotion() {
        listener.saveBagUpdates()
    }
}

interface BagUpdateListener {
    fun saveBagUpdates()
}