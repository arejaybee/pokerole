package com.arejaybee.pokerole_core_sheet.trainer

import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Hyper_Potion
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Super_Potion

class Potion(var uses: Int = 2) {
    val maxUses: Int = uses

    companion object {
        fun getPotion() : Potion {
            return Potion()
        }
        fun getSuperPotion() : Potion {
            return Potion( 4)
        }
        fun getHyperPotion() : Potion {
            return Potion(14)
        }
        fun getPotionByType(type: POTION_ENUM) : Potion {
            return when(type) {
                POTION_ENUM.Potion -> getPotion()
                Super_Potion -> getSuperPotion()
                Hyper_Potion -> getHyperPotion()
            }
        }
    }
}