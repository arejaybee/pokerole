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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.MainActivity
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.views.pokemon.PokemonCardView
import com.arejaybee.pokerole_core_sheet.views.pokemon.PokemonSkill

enum class PokemonPage { POKEMON, SKILLS, MOVES }
var selectedPokemonPage by mutableStateOf(PokemonPage.POKEMON)

@Composable
fun PokemonContentView(newContext: MainActivity) {
    context = newContext
    Box(Modifier.background(colorResource(id = R.color.background_primary))) {
        Row(modifier = Modifier.padding(10.dp)) {
            PokemonNavButtons(modifier = Modifier
                .weight(0.15f)
                .fillMaxWidth())
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            val pageModifier = Modifier
                .weight(0.85f)
                .align(Alignment.CenterVertically)
            when (selectedPokemonPage) {
                PokemonPage.POKEMON -> PokemonCardView(pageModifier)
                PokemonPage.MOVES -> Text("")
                PokemonPage.SKILLS -> PokemonSkill(pageModifier)
                else -> Text("")
            }
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
        ActionButton(modifier= roundedMod.fillMaxWidth(), onClick = {
                context.selectedPokemon.value = null
        }) {
            Text(text = stringResource(id = R.string.nav_pokemon_to_trainer), textAlign = TextAlign.Center)
        }
    }
}