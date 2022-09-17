package com.arejaybee.pokerole_core_sheet.views.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.pokemon.PokemonStatus
import com.arejaybee.pokerole_core_sheet.views.StatusDropdown
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod

@Composable
fun PokemonBattleView(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        StatSection(Modifier.weight(1f))
        MoveSection(Modifier.weight(2f))
    }
}

@Composable
fun StatSection(modifier: Modifier) {
    val pokemon = context.selectedPokemon.value!!
    val inputList1: List<MutableState<String>> = listOf(
        remember { mutableStateOf(pokemon.hp.toString()) },
        remember { mutableStateOf(pokemon.will.toString()) },
        remember { mutableStateOf(pokemon.item) },
        remember { mutableStateOf(pokemon.status.toString()) },
        remember { mutableStateOf(pokemon.initiative.toString()) },
    )
    val inputList2: List<MutableState<String>> = listOf(
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

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        inputList1.forEachIndexed { index, input ->
            if (index == 3) {
                StatusDropdown(
                    modifier = Modifier.weight(1f),
                    selection = pokemon.status.name.replace("_", " ")
                ) {
                    pokemon.status = PokemonStatus.valueOf(it.name)
                }
            } else {
                TextField(
                    modifier = roundedMod.weight(1f),
                    label = { Text(stringResource(id = stringList[index])) },
                    value = input.value,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        try {
                            input.value = it
                            val list: MutableList<MutableState<String>> = mutableListOf()
                            list.addAll(inputList1)
                            list.addAll(inputList2)
                            pokemon.updateBattleStats(list)
                        } catch (e: Exception) {
                            input.value = ""
                        }
                    }
                )
            }
        }
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        inputList2.forEachIndexed { index, input ->
            TextField(
                modifier = roundedMod.weight(1f),
                label = { Text(stringResource(id = stringList[index+inputList1.size])) },
                value = input.value,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        input.value = it
                        val list: MutableList<MutableState<String>> = mutableListOf()
                        list.addAll(inputList1)
                        list.addAll(inputList2)
                        pokemon.updateBattleStats(list)
                    } catch (e: Exception) {
                        input.value = ""
                    }
                }
            )
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

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        for (index in 0 until 2) {
            TextField(
                modifier = roundedMod.weight(1f).fillMaxHeight(),
                label = { Text(stringResource(id = string.pokemon_battle_move)) },
                value = inputList[index].value,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                onValueChange = {
                    try {
                        pokemon.moves[index] = it
                        inputList[index].value = pokemon.moves[index]
                        pokemon.moves.clear()
                        pokemon.moves = mutableListOf(
                            inputList[0].value,
                            inputList[1].value,
                            inputList[2].value,
                            inputList[3].value
                        )
                    } catch (e: Exception) {
                        inputList[index].value = ""
                    }
                }
            )
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        for (index in 2 until 4) {
            TextField(
                modifier = roundedMod.weight(1f).fillMaxHeight(),
                label = { Text(stringResource(id = string.pokemon_battle_move)) },
                value = inputList[index].value,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                onValueChange = {
                    try {
                        pokemon.moves[index] = it
                        inputList[index].value = pokemon.moves[index]
                        pokemon.moves.clear()
                        pokemon.moves = mutableListOf(
                            inputList[0].value,
                            inputList[1].value,
                            inputList[2].value,
                            inputList[3].value
                        )
                    } catch (e: Exception) {
                        inputList[index].value = ""
                    }
                }
            )
        }
    }
}