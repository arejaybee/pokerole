package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.ActionButton
import com.arejaybee.pokerole_core_sheet.views.GlideImage
import com.arejaybee.pokerole_core_sheet.views.LabeledButton
import com.arejaybee.pokerole_core_sheet.views.NatureDropdown
import com.arejaybee.pokerole_core_sheet.views.context
import com.arejaybee.pokerole_core_sheet.views.roundedMod


@Composable
fun TrainerCard(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .weight(0.4f)
                .padding(end = 10.dp, bottom = 10.dp)
                .fillMaxHeight(),
            onClick = { context.imageUtil.selectTrainerImage.launch("image/*")}
        ) {
            GlideImage(context.imageUtil.trainerImageUri.value, R.drawable.ic_playerplaceholder)
        }
        Column(modifier = Modifier.weight(0.6f), verticalArrangement = Arrangement.SpaceEvenly) {
            EditableGridRow(modifier = Modifier.weight(0.75f))
            RowThree(modifier = Modifier.weight(0.25f))
        }
    }
}

@Composable
fun EditableGridRow(modifier: Modifier) {
    var inputName by remember { mutableStateOf(context.trainer.value.name) }
    var inputAge by remember { mutableStateOf(context.trainer.value.age) }
    var inputMoney by remember { mutableStateOf(context.trainer.value.money) }
    var inputHp by remember { mutableStateOf(context.trainer.value.curHp.toString()) }
    var inputWill by remember { mutableStateOf(context.trainer.value.will.toString()) }
    var inputExp by remember { mutableStateOf(context.trainer.value.experience.toString()) }
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                TextField(
                    modifier = roundedMod,
                    label = { Text(stringResource(id = R.string.trainer_card_name)) },
                    value = inputName,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    onValueChange = {
                        context.trainer.value.name = it
                        inputName = context.trainer.value.name
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
                        context.trainer.value.age = it
                        inputAge = context.trainer.value.age
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
                        context.trainer.value.money = it
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
                            context.trainer.value.curHp = Integer.parseInt(it)
                            inputHp = context.trainer.value.curHp.toString()
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
                            context.trainer.value.will = Integer.parseInt(it)
                            inputWill = context.trainer.value.will.toString()
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
                            context.trainer.value.experience = Integer.parseInt(it)
                            inputExp = context.trainer.value.experience.toString()
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
        NatureDropdown(
            modifier = Modifier.weight(1f),
            context.trainer.value.nature.name
        ) {
            context.trainer.value.nature = Nature.valueOf(it.name)
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
        var conceptTitle by remember { mutableStateOf(context.trainer.value.concept.summary) }
        if (openDialog) {
            Dialog(onDismissRequest = { openDialog = false }) {
                var conceptDetails by remember { mutableStateOf(context.trainer.value.concept.details) }
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
                                context.trainer.value.concept.summary = it
                                context.trainer.value.saveData()
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
                                context.trainer.value.concept.details = it
                                context.trainer.value.saveData()
                                conceptDetails = it
                            }
                        )
                    }
                }
            }
        }

        if(conceptTitle.isEmpty()) {
            ActionButton(
                modifier = roundedMod
                    .weight(1f)
                    .fillMaxSize(),
                onClick = {
                    openDialog = true
                },
            ) {
                Text(stringResource(id = R.string.trainer_card_concept))
            }
        } else {
            LabeledButton(
                modifier = Modifier.weight(1f),
                label = stringResource(id = R.string.trainer_card_concept),
                onClick = {
                    openDialog = true
                },
            ) {
                Text(conceptTitle)
            }
        }
    }
}
