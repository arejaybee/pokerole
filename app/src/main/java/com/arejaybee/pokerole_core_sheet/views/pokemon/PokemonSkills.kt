package com.arejaybee.pokerole_core_sheet.views.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.window.Dialog
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.views.ActionButton
import com.arejaybee.pokerole_core_sheet.views.AttributeDialog
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonSurvival
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod

@Composable
fun PokemonSkill(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillsSection(modifier = Modifier.weight(0.55f))
        AttributeButtons(modifier = Modifier.weight(0.45f))
    }
}

@Composable
private fun SkillsSection(modifier: Modifier) {
    var inputStrength by remember { mutableStateOf(context.selectedPokemon.value?.strength.toString()) }
    var inputDexterity by remember { mutableStateOf(context.selectedPokemon.value?.dexterity.toString()) }
    var inputVitality by remember { mutableStateOf(context.selectedPokemon.value?.vitality.toString()) }
    var inputSpecial by remember { mutableStateOf(context.selectedPokemon.value?.special.toString()) }
    var inputInsight by remember { mutableStateOf(context.selectedPokemon.value?.insight.toString()) }

    var inputTough by remember { mutableStateOf(context.selectedPokemon.value?.tough.toString()) }
    var inputCool by remember { mutableStateOf(context.selectedPokemon.value?.cool.toString()) }
    var inputBeauty by remember { mutableStateOf(context.selectedPokemon.value?.beauty.toString()) }
    var inputCute by remember { mutableStateOf(context.selectedPokemon.value?.cute.toString()) }
    var inputSmart by remember { mutableStateOf(context.selectedPokemon.value?.smart.toString()) }
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(5),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
    ) {
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_strength)) },
                value = inputStrength,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.strength = Integer.parseInt(it)
                        inputStrength = context.selectedPokemon.value?.strength.toString()
                    } catch (e: Exception) {
                        inputStrength = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_dexterity)) },
                value = inputDexterity,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.dexterity = Integer.parseInt(it)
                        inputDexterity = context.selectedPokemon.value?.dexterity.toString()
                    } catch (e: Exception) {
                        inputDexterity = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_vitality)) },
                value = inputVitality,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.vitality = Integer.parseInt(it)
                        inputVitality = context.selectedPokemon.value?.vitality.toString()
                    } catch (e: Exception) {
                        inputVitality = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_special)) },
                value = inputSpecial,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.special = Integer.parseInt(it)
                        inputSpecial = context.selectedPokemon.value?.special.toString()
                    } catch (e: Exception) {
                        inputSpecial = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_insight)) },
                value = inputInsight,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.insight = Integer.parseInt(it)
                        inputInsight = context.selectedPokemon.value?.insight.toString()
                    } catch (e: Exception) {
                        inputInsight = ""
                    }
                }
            )
        }

        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_tough)) },
                value = inputTough,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.tough = Integer.parseInt(it)
                        inputTough = context.selectedPokemon.value?.tough.toString()
                    } catch (e: Exception) {
                        inputTough = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_cool)) },
                value = inputCool,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.cool = Integer.parseInt(it)
                        inputCool = context.selectedPokemon.value?.cool.toString()
                    } catch (e: Exception) {
                        inputCool = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_beauty)) },
                value = inputBeauty,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.beauty = Integer.parseInt(it)
                        inputBeauty = context.selectedPokemon.value?.beauty.toString()
                    } catch (e: Exception) {
                        inputBeauty = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_cute)) },
                value = inputCute,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.cute = Integer.parseInt(it)
                        inputCute = context.selectedPokemon.value?.cute.toString()
                    } catch (e: Exception) {
                        inputCute = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_smart)) },
                value = inputSmart,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.smart = Integer.parseInt(it)
                        inputSmart = context.selectedPokemon.value?.smart.toString()
                    } catch (e: Exception) {
                        inputSmart = ""
                    }
                }
            )
        }
    }
}

@Composable
private fun AttributeButtons(modifier: Modifier) {
    var buttonIndex by remember { mutableStateOf(0) }
    var openDialog by remember { mutableStateOf(false) }
    var inputHappiness by remember { mutableStateOf(context.selectedPokemon.value?.happiness.toString()) }
    var inputLoyalty by remember { mutableStateOf(context.selectedPokemon.value?.loyalty.toString()) }
    var inputDisobedience by remember { mutableStateOf(context.selectedPokemon.value?.disobedience.toString()) }
    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            when (buttonIndex) {
                0 -> AttributeDialog(PokemonFight)
                1 -> AttributeDialog(PokemonSurvival)
                2 -> AttributeDialog(PokemonContest)
            }
        }
    }
    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 20.dp),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val buttons = listOf(
            R.string.skills_fight,
            R.string.skills_survival,
            R.string.skills_contest
        )
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_happiness)) },
                value = inputHappiness,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.happiness = Integer.parseInt(it)
                        inputHappiness = context.selectedPokemon.value?.happiness.toString()
                    } catch (e: Exception) {
                        inputHappiness = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_loyalty)) },
                value = inputLoyalty,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.loyalty = Integer.parseInt(it)
                        inputLoyalty = context.selectedPokemon.value?.loyalty.toString()
                    } catch (e: Exception) {
                        inputLoyalty = ""
                    }
                }
            )
        }
        item {
            TextField(
                modifier = roundedMod,
                label = { Text(stringResource(id = string.skills_disobedience)) },
                value = inputDisobedience,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try {
                        context.selectedPokemon.value?.disobedience = Integer.parseInt(it)
                        inputDisobedience = context.selectedPokemon.value?.disobedience.toString()
                    } catch (e: Exception) {
                        inputDisobedience = ""
                    }
                }
            )
        }
        items(3) { index ->
            ActionButton(
                modifier = Modifier,
                onClick = {
                    buttonIndex = index
                    openDialog = true
                }
            ) {
                Text(text = stringResource(id = buttons[index]))
            }
        }
    }
}