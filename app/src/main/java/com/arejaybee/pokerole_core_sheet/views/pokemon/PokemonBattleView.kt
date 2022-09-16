package com.arejaybee.pokerole_core_sheet.views.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.pokemon.PokemonStatus
import com.arejaybee.pokerole_core_sheet.views.StatusDropdown
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod


//HP, WILL, ITEM, STATUS, INITIATIVE,
// ACCURACY, DAMAGE, EVASION, DEF, SP. DEF
//Moves
@Composable
fun PokemonBattleView(modifier: Modifier) {
    Column(modifier = modifier) {
        StatSection(Modifier.weight(0.55f))
        MoveSection(Modifier.weight(0.45f))
    }
}

@Composable
fun StatSection(modifier: Modifier) {
    val pokemon = context.selectedPokemon.value!!
    val inputList: List<MutableState<String>> = listOf(
        remember { mutableStateOf(pokemon.hp.toString()) },
        remember { mutableStateOf(pokemon.will.toString()) },
        remember { mutableStateOf(pokemon.item) },
        remember { mutableStateOf(pokemon.status.toString()) },
        remember { mutableStateOf(pokemon.initiative.toString()) },

        remember { mutableStateOf(pokemon.accuracy.toString()) },
        remember { mutableStateOf(pokemon.damage.toString()) },
        remember { mutableStateOf(pokemon.evasion.toString()) },
        remember { mutableStateOf(pokemon.defense.toString()) },
        remember { mutableStateOf(pokemon.specialDefense.toString()) },
    )
    val stringList = listOf(
        R.string.pokemon_battle_hp,
        R.string.pokemon_battle_will,
        R.string.pokemon_battle_item,
        R.string.pokemon_battle_status,
        R.string.pokemon_battle_initiative,

        R.string.pokemon_battle_accuracy,
        R.string.pokemon_battle_damage,
        R.string.pokemon_battle_evasion,
        R.string.pokemon_battle_defense,
        R.string.pokemon_battle_special_defense,
    )
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(5),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            itemsIndexed(inputList) { index, input ->
                if(index == 3) {
                    StatusDropdown(
                        selection = pokemon.status.name.replace("_"," ")
                    ) {
                        pokemon.status = PokemonStatus.valueOf(it.name)
                    }
                } else {
                    TextField(
                        modifier = roundedMod,
                        label = { Text(stringResource(id = stringList[index])) },
                        value = input.value,
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            try {
                                input.value = it
                                pokemon.updateBattleStats(inputList)
                            } catch (e: Exception) {
                                input.value = ""
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MoveSection(modifier: Modifier) {
    val pokemon = context.selectedPokemon.value!!
    val inputList: List<MutableState<String>> = listOf(
        remember { mutableStateOf(pokemon.moves[0]) },
        remember { mutableStateOf(pokemon.moves[1]) },
        remember { mutableStateOf(pokemon.moves[2]) },
        remember { mutableStateOf(pokemon.moves[3]) }
    )
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(4),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            items(4) { index ->
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = R.string.pokemon_battle_move)) },
                    value = inputList[index].value,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        try {
                            pokemon.moves[index] = it
                            inputList[index].value = pokemon.moves[index]
                            pokemon.moves.clear()
                            pokemon.moves = mutableListOf(inputList[0].value, inputList[1].value,inputList[2].value,inputList[3].value)
                        } catch (e: Exception) {
                            inputList[index].value = ""
                        }
                    }
                )
            }
        }
    }
}