package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerKnowledge
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerSurvival
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod

@Composable
fun TrainerSkill(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        SkillsSection(modifier = modifier.weight(1f))
        AttributeButtons(modifier = modifier.weight(1f))
    }
}

@Composable
private fun SkillsSection(modifier: Modifier = Modifier) {
    var inputStrength by remember { mutableStateOf(context.trainer.value.strength.toString()) }
    var inputDexterity by remember { mutableStateOf(context.trainer.value.dexterity.toString()) }
    var inputVitality by remember { mutableStateOf(context.trainer.value.vitality.toString()) }
    var inputInsight by remember { mutableStateOf(context.trainer.value.insight.toString()) }
    var inputTough by remember { mutableStateOf(context.trainer.value.tough.toString()) }
    var inputCool by remember { mutableStateOf(context.trainer.value.cool.toString()) }
    var inputBeauty by remember { mutableStateOf(context.trainer.value.beauty.toString()) }
    var inputIntelligence by remember { mutableStateOf(context.trainer.value.intelligence.toString()) }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)) {
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_strength)) },
            value = inputStrength,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.strength = Integer.parseInt(it)
                    inputStrength = context.trainer.value.strength.toString()
                } catch (e: Exception) {
                    inputStrength = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_dexterity)) },
            value = inputDexterity,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.dexterity = Integer.parseInt(it)
                    inputDexterity = context.trainer.value.dexterity.toString()
                } catch (e: Exception) {
                    inputDexterity = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_vitality)) },
            value = inputVitality,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.vitality = Integer.parseInt(it)
                    inputVitality = context.trainer.value.vitality.toString()
                } catch (e: Exception) {
                    inputVitality = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_insight)) },
            value = inputInsight,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.insight = Integer.parseInt(it)
                    inputInsight = context.trainer.value.insight.toString()
                } catch (e: Exception) {
                    inputInsight = ""
                }
            }
        )
    }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)) {
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_tough)) },
            value = inputTough,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.tough = Integer.parseInt(it)
                    inputTough = context.trainer.value.tough.toString()
                } catch (e: Exception) {
                    inputTough = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_cool)) },
            value = inputCool,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.cool = Integer.parseInt(it)
                    inputCool = context.trainer.value.cool.toString()
                } catch (e: Exception) {
                    inputCool = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_beauty)) },
            value = inputBeauty,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.beauty = Integer.parseInt(it)
                    inputBeauty = context.trainer.value.beauty.toString()
                } catch (e: Exception) {
                    inputBeauty = ""
                }
            }
        )
        TextField(
            modifier = roundedMod.weight(1f),
            label = { Text(stringResource(id = string.skills_intelligence)) },
            value = inputIntelligence,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try {
                    context.trainer.value.intelligence = Integer.parseInt(it)
                    inputIntelligence = context.trainer.value.intelligence.toString()
                } catch (e: Exception) {
                    inputIntelligence = ""
                }
            }
        )
    }
}

@Composable
private fun AttributeButtons(modifier: Modifier = Modifier) {
    val trainer = context.trainer.value
    var buttonIndex by remember { mutableStateOf(0) }
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            when (buttonIndex) {
                0 -> AttributeDialog(TrainerFight)
                1 -> AttributeDialog(TrainerSurvival)
                2 -> AttributeDialog(TrainerContest)
                3 -> AttributeDialog(TrainerKnowledge)
            }
        }
    }
    val buttons = listOf(
        stringResource(id = R.string.skills_fight) + "(${trainer.fight})",
        stringResource(id = R.string.skills_survival) + "(${trainer.survival})",
        stringResource(id = R.string.skills_contest) + "(${trainer.contest})",
        stringResource(id = R.string.skills_knowledge) + "(${trainer.knowledge})"
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for(index in 0 until 4) {
            ActionButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    buttonIndex = index
                    openDialog = true
                }
            ) {
                Text(text = buttons[index])
            }
        }
    }
}