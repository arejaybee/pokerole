package com.arejaybee.pokerole_core_sheet.views.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.R.drawable
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.pokemon.PokemonType
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.GlideImage
import com.arejaybee.pokerole_core_sheet.views.NatureDropdown
import com.arejaybee.pokerole_core_sheet.views.TypeDropdown
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod

@Composable
fun PokemonCardView(modifier: Modifier) {

    context.imageUtil.selectedPokemon = context.trainer.value.pokemon.indexOf(context.selectedPokemon.value)
    Column(modifier = modifier) {
        Row(Modifier.weight(0.75f)) {
            PokePic(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
            )
            Column(modifier = Modifier.weight(0.6f)) {
                NameSection(Modifier.weight(0.33f))
                TypeSection(Modifier.weight(0.33f))
                NatureSection(Modifier.weight(0.33f))
            }
        }
        SizeSection(Modifier.weight(0.25f))
    }
}

@Composable
fun PokePic(modifier: Modifier) {
    IconButton(
        modifier = modifier.padding(end = 10.dp, bottom = 10.dp),
        onClick = {
            context.imageUtil.selectPokemonImage.launch("image/*")
        }
    ) {
        GlideImage(context.imageUtil.pokemonImageUri.value, drawable.ic_pokeball)
    }
}

@Composable
fun NameSection(modifier: Modifier) {
    var inputName by remember { mutableStateOf(context.selectedPokemon.value?.name.toString()) }
    var inputLevel by remember { mutableStateOf(context.selectedPokemon.value?.level.toString()) }
    var inputXp by remember { mutableStateOf(context.selectedPokemon.value?.exp.toString()) }
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(3),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_name)) },
                    value = inputName,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.name = it
                            inputName = context.selectedPokemon.value?.name.toString()
                        } catch (e: Exception) {
                            inputName = ""
                        }
                    }
                )
            }

            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_level)) },
                    value = inputLevel,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.level = Integer.parseInt(it)
                            inputLevel = context.selectedPokemon.value?.level.toString()
                        } catch (e: Exception) {
                            inputLevel = ""
                        }
                    }
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_experience)) },
                    value = inputXp,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.exp = Integer.parseInt(it)
                            inputXp = context.selectedPokemon.value?.exp.toString()
                        } catch (e: Exception) {
                            inputXp = ""
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TypeSection(modifier: Modifier) {
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(2),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                TypeDropdown(
                    selection = context.selectedPokemon.value?.type1?.name.toString()
                ) {
                    context.selectedPokemon.value?.type1 = PokemonType.valueOf(it.name)
                }
            }
            item {
                TypeDropdown(
                    selection = context.selectedPokemon.value?.type2?.name.toString()
                ) {
                    context.selectedPokemon.value?.type2 = PokemonType.valueOf(it.name)
                }
            }
        }
    }
}

@Composable
fun NatureSection(modifier: Modifier) {
    var inputAbility by remember { mutableStateOf(context.selectedPokemon.value?.ability) }
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(2),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            item {
                NatureDropdown(
                    selection = context.selectedPokemon.value?.nature?.name.toString()
                ) {
                    context.selectedPokemon.value?.nature = Nature.valueOf(it.name)
                }
            }
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_ability)) },
                    value = inputAbility.toString(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.ability = it
                            inputAbility = context.selectedPokemon.value?.ability.toString()
                        } catch (e: Exception) {
                            inputAbility = ""
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun SizeSection(modifier: Modifier) {
    var inputSize by remember { mutableStateOf(context.selectedPokemon.value?.size) }
    var inputWeight by remember { mutableStateOf(context.selectedPokemon.value?.weight) }
    Box(modifier) {
        LazyVerticalGrid(
            columns = Fixed(2),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_size)) },
                    value = inputSize.toString(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.size = it
                            inputSize = context.selectedPokemon.value?.size.toString()
                        } catch (e: Exception) {
                            inputSize = ""
                        }
                    }
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = string.pokemon_card_weight)) },
                    value = inputWeight.toString(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        try {
                            context.selectedPokemon.value?.weight = it
                            inputWeight = context.selectedPokemon.value?.weight.toString()
                        } catch (e: Exception) {
                            inputWeight = ""
                        }
                    }
                )
            }
        }
    }
}