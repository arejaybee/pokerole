package com.arejaybee.pokerole_core_sheet.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.MainActivity
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon

enum class PokemonPage { POKEMON, SKILLS, ATTRIBUTES, MOVES }
var selectedPokemonPage by mutableStateOf(PokemonPage.POKEMON)
lateinit var selectedPokemon: Pokemon

@Composable
fun PokemonContentView(newContext: MainActivity, pokemon: Pokemon) {
    context = newContext
    selectedPokemon = pokemon
    Box(Modifier.background(colorResource(id = R.color.background_primary))) {
        Column(Modifier.padding(10.dp)) {
            Row(Modifier.weight(0.8f)) {
                PokemonNavButtons(modifier = Modifier.weight(0.15f))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                val pageModifier = Modifier.weight(0.85f)
                when (selectedPokemonPage) {
                    PokemonPage.POKEMON -> Text("")
                    PokemonPage.MOVES -> Text("")
                    PokemonPage.SKILLS -> Text("")
                    PokemonPage.ATTRIBUTES -> Text("")
                    else -> Text("")
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            ReturnTrainerButton(modifier = Modifier.weight(0.2f))
        }
    }
}

@Composable
fun PokemonNavButtons(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPokemonPage = PokemonPage.POKEMON
        }) {
            Text(stringResource(id = R.string.nav_pokemon_card), textAlign = TextAlign.Center)
        }

        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPokemonPage = PokemonPage.MOVES
        }) {
            Text(stringResource(id = R.string.nav_pokemon_moves), textAlign = TextAlign.Center)
        }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPokemonPage = PokemonPage.SKILLS
        }) {
            Text(stringResource(id = R.string.nav_pokemon_skills), textAlign = TextAlign.Center)
        }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPokemonPage = PokemonPage.ATTRIBUTES
        }) {
            Text(stringResource(id = R.string.nav_pokemon_attributes), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ReturnTrainerButton(modifier: Modifier) {
    Button(
        modifier= modifier,
        onClick = {
            context.activityPageFlag.value = null
        }
    ) {
        Text(
            text = "Trainer"
        )
    }
}