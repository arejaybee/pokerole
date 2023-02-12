package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.R.drawable
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.trainer.Trainer.Companion.POKEMON_PER_BOX
import com.arejaybee.pokerole_core_sheet.views.GlideImage
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.mainPokemonClick
import com.arejaybee.pokerole_core_sheet.views.pokemonClick
import com.arejaybee.pokerole_core_sheet.views.trainer.PC_STATE.PICKING
import com.arejaybee.pokerole_core_sheet.views.trainer.PC_STATE.SWAPPING

private const val POKEMON_PER_ROW = 5
private const val ROW_COUNT = 4

var pcPokemon by mutableStateOf(listOf<Pokemon>())
var selectedBox by mutableStateOf(-1)
var boxNum by mutableStateOf(0)

@Composable
fun PC(modifier: Modifier) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)) {
        PCMainContent(Modifier.weight(0.9f))
        Text(
            text = "Select a slot, then select a party pokemon to swap",
            modifier = Modifier
                .weight(0.1f)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PCMainContent(modifier: Modifier) {
    val maxBoxes = context.trainer.value.getMaxBoxNum()
    Row(modifier = modifier) {
        Button(
            modifier = Modifier
                .weight(0.075f)
                .align(Alignment.CenterVertically),
            onClick = {
                if (boxNum == 0) {
                    boxNum = maxBoxes - 1
                } else {
                    boxNum--
                }
            }
        ) {
            Image(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Next")
        }
        Column(
            modifier = Modifier
                .weight(.85f)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "BOX ${boxNum + 1}",
                Modifier
                    .weight(.2f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            PCBoxes(Modifier.weight(0.8f))
        }
        Button(
            modifier = Modifier
                .weight(0.075f)
                .align(Alignment.CenterVertically),
            onClick = {
                if (boxNum + 1 == maxBoxes) {
                    boxNum = 0
                } else {
                    boxNum++
                }
            }
        ) {
            Image(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}

@Composable
fun PCBoxes(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        pcPokemon = context.trainer.value.pcPokemon!!.toList()
        for (rowCount in 0 until ROW_COUNT) {
            Row(
                modifier = Modifier.weight(0.9f),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                for (pcIndex in 0 until POKEMON_PER_ROW) {
                    val index = pcIndex + POKEMON_PER_ROW * rowCount + POKEMON_PER_BOX * boxNum
                    GlideImage(
                        pcPokemon[index].profilePicture,
                        drawable.ic_pcbox,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onClickPcSlot(index)
                            }
                    )
                }
            }
        }
    }
}

fun onClickPcSlot(index: Int) {
    when (STATE) {
        PICKING -> {
            selectedBox = index
            pokemonClick = { pokemon: Pokemon ->
                context.trainer.value.swapPokemonWithPC(
                    context.trainer.value.pokemon.indexOf(pokemon),
                    index
                )
                STATE = PICKING
                context.redraw()
            }
            STATE = SWAPPING
        }
        SWAPPING -> {
            pokemonClick = mainPokemonClick
            context.trainer.value.swapPcPokemonWithPC(
                selectedBox,
                index
            )
            STATE = PICKING
        }
    }
    context.redraw()
}

enum class PC_STATE { PICKING, SWAPPING }

private var STATE = PC_STATE.PICKING