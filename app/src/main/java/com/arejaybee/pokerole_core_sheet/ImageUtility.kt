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

    val pokemonImageUri = mutableStateOf<Uri?>(null)
    var selectedPokemon: Int = -1
    set(value) {
        field = value
        val uriString = context.trainer.value.pokemon[selectedPokemon].profilePicture
        pokemonImageUri.value =  if(uriString == "null" || uriString.isBlank()) null else Uri.parse(uriString)
    }

    val selectPokemonImage = context.registerForActivityResult(GetContent()) {
        pokemonImageUri.value = it
        context.trainer.value.pokemon[selectedPokemon].profilePicture = it.toString()
    }

    var badgeImageUri = mutableListOf(
        mutableStateOf(getUriForString(context.trainer.value.badges[0])),
        mutableStateOf(getUriForString(context.trainer.value.badges[1])),
        mutableStateOf(getUriForString(context.trainer.value.badges[2])),
        mutableStateOf(getUriForString(context.trainer.value.badges[3])),
        mutableStateOf(getUriForString(context.trainer.value.badges[4])),
        mutableStateOf(getUriForString(context.trainer.value.badges[5])),
        mutableStateOf(getUriForString(context.trainer.value.badges[6])),
        mutableStateOf(getUriForString(context.trainer.value.badges[7]))
    )
    var selectedBadge: Int = -1
    val selectBadgeImage = context.registerForActivityResult(GetContent()) {
        val list = mutableListOf<String>()
        badgeImageUri[selectedBadge].value = it
        list.addAll(context.trainer.value.badges)
        list[selectedBadge] = it.toString()
        context.trainer.value.badges.clear()
        context.trainer.value.badges = mutableListOf(
            list[0],
            list[1],
            list[2],
            list[3],
            list[4],
            list[5],
            list[6],
            list[7]
        )
    }

    fun updateBadgeImages() {
        badgeImageUri.clear()
        badgeImageUri = mutableListOf(
            mutableStateOf(getUriForString(context.trainer.value.badges[0])),
            mutableStateOf(getUriForString(context.trainer.value.badges[1])),
            mutableStateOf(getUriForString(context.trainer.value.badges[2])),
            mutableStateOf(getUriForString(context.trainer.value.badges[3])),
            mutableStateOf(getUriForString(context.trainer.value.badges[4])),
            mutableStateOf(getUriForString(context.trainer.value.badges[5])),
            mutableStateOf(getUriForString(context.trainer.value.badges[6])),
            mutableStateOf(getUriForString(context.trainer.value.badges[7]))
        )
    }

    private fun getUriForString(uriString: String): Uri? {
        return if(uriString == "null" || uriString.isBlank()) null else Uri.parse(uriString)
    }
}