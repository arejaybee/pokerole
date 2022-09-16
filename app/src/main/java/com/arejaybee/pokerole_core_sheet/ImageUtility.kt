package com.arejaybee.pokerole_core_sheet

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.runtime.mutableStateOf

class ImageUtility(val context: MainActivity) {
    val trainerImageUri = mutableStateOf<Uri?>(null)

    val selectTrainerImage = context.registerForActivityResult(GetContent()) {
        trainerImageUri.value = it
        context.trainer.value.profilePicture = it.toString()
    }

    var selectedPokemon: Int = -1
    set(value) {
        field = value
        val uriString = context.trainer.value.pokemon[selectedPokemon].profilePicture
        pokemonImageUri.value =  if(uriString == "null" || uriString.isBlank()) null else Uri.parse(uriString)
    }
    val pokemonImageUri = mutableStateOf<Uri?>(null)

    val selectPokemonImage = context.registerForActivityResult(GetContent()) {
        pokemonImageUri.value = it
        context.trainer.value.pokemon[selectedPokemon].profilePicture = it.toString()
    }
}