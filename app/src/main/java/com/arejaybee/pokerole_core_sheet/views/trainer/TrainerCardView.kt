package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.R.color
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.ActionButton
import com.arejaybee.pokerole_core_sheet.views.roundedMod
import com.arejaybee.pokerole_core_sheet.views.trainer

@Composable
fun TrainerCard(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier.weight(0.4f).fillMaxHeight().padding(20.dp),
            painter = painterResource(id = R.drawable.ic_playerplaceholder),
            contentDescription = "Player Portrait"
        )
        Column(modifier = Modifier.weight(0.6f), verticalArrangement = Arrangement.SpaceEvenly) {
            EditableGridRow(modifier = Modifier.weight(0.75f))
            RowThree(modifier = Modifier.weight(0.25f))
        }
    }
}

@Composable
fun EditableGridRow(modifier: Modifier) {
    var inputName by remember { mutableStateOf(trainer.name) }
    var inputAge by remember { mutableStateOf(trainer.age) }
    var inputMoney by remember { mutableStateOf(trainer.money) }
    var inputHp by remember { mutableStateOf(trainer.curHp.toString()) }
    var inputWill by remember { mutableStateOf(trainer.will.toString()) }
    var inputExp by remember { mutableStateOf(trainer.experience.toString()) }
    Box(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = R.string.trainer_card_name)) },
                    value = inputName,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        trainer.name = it
                        inputName = trainer.name
                    }
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    value = inputAge,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    label = { Text(stringResource(id = R.string.trainer_card_age)) },
                    onValueChange = {
                        trainer.age = it
                        inputAge = trainer.age
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    value = inputMoney,
                    label = { Text(stringResource(id = R.string.trainer_card_money)) },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        trainer.money = it
                        inputMoney = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    value = inputHp,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    label = { Text(stringResource(id = R.string.trainer_card_hp)) },
                    onValueChange = {
                        try {
                            trainer.curHp = Integer.parseInt(it)
                            inputHp = trainer.curHp.toString()
                        } catch (e: Exception) {
                            inputHp = ""
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    value = inputWill,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    label = { Text(stringResource(id = R.string.trainer_card_will)) },
                    onValueChange = {
                        try {
                            trainer.will = Integer.parseInt(it)
                            inputWill = trainer.will.toString()
                        } catch (e: Exception) {
                            inputWill = ""
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item {
                TextField(
                    modifier = roundedMod,
                    value = inputExp,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    label = { Text(stringResource(id = R.string.trainer_card_experience)) },
                    onValueChange = {
                        try {
                            trainer.experience = Integer.parseInt(it)
                            inputExp = trainer.experience.toString()
                        } catch (e: Exception) {
                            inputExp = ""
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }

}

@Composable
fun RowThree(modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        var natureExpanded by remember { mutableStateOf(false) }
        Box(
            contentAlignment = Alignment.Center,
            modifier = roundedMod
                .clickable { natureExpanded = true }
                .weight(1f)
                .fillMaxHeight()
                .background(color = colorResource(id = color.button_primary))
        ) {
            Text(
                text = trainer.nature.name,
                textAlign = TextAlign.Center
            )
            DropdownMenu(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),
                expanded = natureExpanded,
                onDismissRequest = { natureExpanded = false }) {
                Nature.values().forEach { nature ->
                    DropdownMenuItem(onClick = {
                        trainer.nature = Nature.valueOf(nature.name)
                        natureExpanded = false
                    }) {
                        Text(nature.name)
                    }
                }
            }
        }
        ConceptButton(Modifier.weight(1f))
    }
}

@Composable
fun ConceptButton(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var openDialog by remember { mutableStateOf(false) }
        val conceptSummary =
            trainer.concept.summary.ifEmpty { stringResource(id = string.trainer_card_concept) }
        var conceptTitle by remember { mutableStateOf(conceptSummary) }
        if (openDialog) {
            Dialog(onDismissRequest = { openDialog = false }) {
                var conceptDetails by remember { mutableStateOf(trainer.concept.details) }
                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    item {
                        TextField(
                            value = conceptTitle,
                            label = { Text(stringResource(id = R.string.trainer_card_concept_summary)) },
                            onValueChange = {
                                trainer.concept.summary = it
                                trainer.saveData()
                                conceptTitle = it
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                    item {
                        TextField(
                            value = conceptDetails,
                            label = { Text(stringResource(id = R.string.trainer_card_concept_details)) },
                            onValueChange = {
                                trainer.concept.details = it
                                trainer.saveData()
                                conceptDetails = it
                            }
                        )
                    }
                }
            }
        }
        ActionButton(
            modifier = roundedMod
                .weight(1f)
                .fillMaxHeight(),
            onClick = {
                openDialog = true
            },
        ) {
            Text(conceptTitle)
        }
    }
}
