package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.roundedMod
import com.arejaybee.pokerole_core_sheet.views.trainer

@Composable
fun TrainerCard(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //left side of the card
            NonEditablePlayerFields(Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(5.dp))
            //right side of the card
            EditablePlayerFields(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier.padding(horizontal = 50.dp), text = "")
            Button(
                modifier = roundedMod
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = { },
            ) {
                Text("Concept: ${trainer.concept.summary}")
            }
            Text(modifier = Modifier.padding(horizontal = 50.dp), text = "")
        }
    }
}

@Composable
fun EditablePlayerFields(modifier: Modifier) {
    Column(modifier = modifier) {
        var inputMoney by remember { mutableStateOf(trainer.money) }
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            var inputHp by remember { mutableStateOf(trainer.curHp.toString()) }
            var inputWill by remember { mutableStateOf(trainer.will.toString()) }
            var inputExp by remember { mutableStateOf(trainer.experience.toString()) }
            TextField(
                modifier = roundedMod
                    .weight(1f)
                    .fillMaxWidth(),
                value = inputHp,
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
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            TextField(
                modifier = roundedMod
                    .weight(1f)
                    .fillMaxWidth(),
                value = inputWill,
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
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            TextField(
                modifier = roundedMod
                    .weight(1f)
                    .fillMaxWidth(),
                value = inputExp,
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
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            modifier = roundedMod
                .weight(1f)
                .fillMaxWidth(),
            value = inputMoney,
            label = { Text(stringResource(id = R.string.trainer_card_money)) },
            onValueChange = {
                trainer.money = it
                inputMoney = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun NonEditablePlayerFields(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var inputName by remember { mutableStateOf(trainer.name) }
        var inputAge by remember { mutableStateOf(trainer.age) }
        TextField(
            modifier = roundedMod
                .weight(1f)
                .fillMaxWidth(),
            label = { Text(stringResource(id = R.string.trainer_card_name)) },
            value = inputName,
            onValueChange = {
                trainer.name = it
                inputName = trainer.name
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = roundedMod
                .weight(1f)
                .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                var natureExpanded by remember { mutableStateOf(false) }
                Box(contentAlignment = Alignment.Center, modifier = Modifier
                    .clickable { natureExpanded = true }
                    .fillMaxHeight()
                    .fillMaxWidth()) {
                    Text(
                        text = trainer.nature.name,
                        textAlign = TextAlign.Center
                    )
                    DropdownMenu(
                        modifier = Modifier
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
            }
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f),
                    value = inputAge,
                    label = { Text(stringResource(id = R.string.trainer_card_age)) },
                    onValueChange = {
                        trainer.age = it
                        inputAge = trainer.age
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}
