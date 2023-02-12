package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.R.color
import com.arejaybee.pokerole_core_sheet.R.drawable
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.views.GlideImage
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.mainPokemonClick
import com.arejaybee.pokerole_core_sheet.views.pokemonClick
import com.arejaybee.pokerole_core_sheet.views.trainer.PC_STATE.PICKING
import com.arejaybee.pokerole_core_sheet.views.trainer.PC_STATE.SWAPPING

private const val ROW_COUNT = 4
var selectedBox by mutableStateOf(-1)
enum class PC_STATE { PICKING, SWAPPING }
private var CURRENT_STATE by mutableStateOf(PICKING)
@Composable
fun PC(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        HorizontalScrollScreen(Modifier.weight(0.9f))
        Text(
            text = stringResource(
                id =
                if (CURRENT_STATE == PICKING) string.pc_instructions_picking
                else string.pc_instructions_swapping
            ),
            modifier = Modifier
                .weight(0.1f)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HorizontalScrollScreen(modifier: Modifier) {
    // a wrapper to fill the entire screen
    Box(modifier = modifier.fillMaxSize()) {
        // BowWithConstraints will provide the maxWidth used below
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            // LazyRow to display your items horizontally
            LazyHorizontalGrid(
                rows = GridCells.Fixed(ROW_COUNT),
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyGridState(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(context.trainer.value.pcPokemon!!) { index, item ->
                    if (selectedBox == index) {
                        GlideImage(
                            item.profilePicture,
                            drawable.ic_pcbox,
                            modifier = Modifier
                                .clickable {
                                    onClickPcSlot(index)
                                }
                                .background(Color(color.button_primary))
                        )
                    } else {
                        GlideImage(
                            item.profilePicture,
                            drawable.ic_pcbox,
                            modifier = Modifier
                                .clickable {
                                    onClickPcSlot(index)
                                }
                        )
                    }
                }
            }
        }
    }
}

fun onClickPcSlot(index: Int) {
    when (CURRENT_STATE) {
        PICKING -> {
            selectedBox = index
            pokemonClick = { pokemon: Pokemon ->
                context.trainer.value.swapPokemonWithPC(
                    context.trainer.value.pokemon.indexOf(pokemon),
                    index
                )
                CURRENT_STATE = PICKING
                selectedBox = -1
                pokemonClick = mainPokemonClick
                context.redraw()
            }
            CURRENT_STATE = SWAPPING
        }
        SWAPPING -> {
            pokemonClick = mainPokemonClick
            context.trainer.value.swapPcPokemonWithPC(
                selectedBox,
                index
            )
            CURRENT_STATE = PICKING
            selectedBox = -1
        }
    }
    context.redraw()
}
