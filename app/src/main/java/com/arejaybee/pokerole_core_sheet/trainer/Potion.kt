package com.arejaybee.pokerole_core_sheet.trainer

import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Hyper_Potion
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Super_Potion

class Potion(@Transient var listener: PotionListener, uses: Int = 2) {
    var uses: Int = uses
    set(value) {
        field = value
        listener.savePotion()
    }
    val maxUses: Int = uses
    interface PotionListener {
        fun savePotion()
    }

    companion object {
        private fun getPotion(listener: PotionListener) : Potion {
            return Potion(listener)
        }
        private fun getSuperPotion(listener: PotionListener) : Potion {
            return Potion( listener, 4)
        }
        private fun getHyperPotion(listener: PotionListener) : Potion {
            return Potion(listener,14)
        }
        fun getPotionByType(listener: PotionListener, type: POTION_ENUM) : Potion {
            return when(type) {
                POTION_ENUM.Potion -> getPotion(listener)
                Super_Potion -> getSuperPotion(listener)
                Hyper_Potion -> getHyperPotion(listener)
            }
        }
    }
}