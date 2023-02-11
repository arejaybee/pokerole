package com.arejaybee.pokerole_core_sheet

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.trainer.Trainer
import com.arejaybee.pokerole_core_sheet.views.MainContentView
import com.arejaybee.pokerole_core_sheet.views.Page
import com.arejaybee.pokerole_core_sheet.views.PokemonContentView
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.selectedPage

class MainActivity : AppCompatActivity() {
    val trainer = mutableStateOf(Trainer(this))
    val selectedPokemon = mutableStateOf<Pokemon?>(null)
    val imageUtil = ImageUtility(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadTrainer()
        setContent {
            selectedPokemon.value?.let {
                PokemonContentView(this)
            }?: MainContentView(this)
        }
    }

    private fun loadTrainer() {
        try {
            trainer.value = Trainer.loadData(this)
            imageUtil.trainerImageUri.value = if(trainer.value.profilePicture.isEmpty()) null else Uri.parse(trainer.value.profilePicture)
        } catch (e: Exception) {
            trainer.value = Trainer(this)
            imageUtil.trainerImageUri.value = if(trainer.value.profilePicture.isEmpty()) null else Uri.parse(trainer.value.profilePicture)
        }
        imageUtil.updateBadgeImages()
    }

    fun goToPokemonView(pokemon: Pokemon) {
        context.selectedPokemon.value = pokemon
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        context.selectedPokemon.value?.let {
            selectedPage = Page.TRAINER_CARD
            context.selectedPokemon.value = null
        }?: run {
            super.onBackPressed()
        }
    }
}