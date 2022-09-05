package com.arejaybee.pokerole_core_sheet.trainer

import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Hyper_Potion
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Super_Potion

enum class POTION_ENUM {Potion, Super_Potion, Hyper_Potion}
class Bag(@Transient var listener: BagUpdateListener) {
    val badges = mutableListOf<Badge>()
    val battleItems = listOf("","","","","")
    val mainItems = listOf("","","","","","","","","","","","","","","","","","","","","","","","")
    private val potionList = mutableListOf<Potion>()
    private val superPotionList = mutableListOf<Potion>()
    private val hyperPotionList = mutableListOf<Potion>()

    fun addPotion(type: POTION_ENUM) {
        when(type) {
            POTION_ENUM.Potion -> potionList.add(Potion.getPotionByType(type))
            Super_Potion -> superPotionList.add(Potion.getPotionByType(type))
            Hyper_Potion -> hyperPotionList.add(Potion.getPotionByType(type))
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
}

interface BagUpdateListener {
    fun saveBagUpdates()
}