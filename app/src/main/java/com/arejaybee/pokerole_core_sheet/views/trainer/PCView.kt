package com.arejaybee.pokerole_core_sheet.views.trainer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.R.drawable
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.views.GlideImage
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.mainPokemonClick
import com.arejaybee.pokerole_core_sheet.views.pokemonClick

private const val POKEMON_PER_ROW = 6
private const val ROW_COUNT = 5

var pcPokemon by mutableStateOf(listOf<Pokemon>())

@Composable
fun PC(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        pcPokemon = context.trainer.value.pcPokemon!!.toList()
        for (rowCount in 0 until ROW_COUNT) {
            Row(
                modifier = Modifier.weight(0.9f),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                for (pcIndex in 0 until POKEMON_PER_ROW) {
                    Log.e("ROBERT","$rowCount - $pcIndex")
                    GlideImage(
                        pcPokemon[pcIndex + POKEMON_PER_ROW * rowCount].profilePicture,
                        drawable.ic_pokeball,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                pokemonClick = { pokemon: Pokemon ->
                                    context.trainer.value.swapPokemonWithPC(
                                        context.trainer.value.pokemon.indexOf(pokemon),
                                        pcIndex
                                    )
                                    pokemonClick = mainPokemonClick
                                    context.redraw()
                                }
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun test() {
    PC(Modifier)
}