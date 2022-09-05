package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.Utility.MyCheckBox
import com.arejaybee.pokerole_core_sheet.views.ActionButton
import com.arejaybee.pokerole_core_sheet.views.trainer

val checkboxNumber = 5

@Composable
fun Skill(modifier: Modifier) {
    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.weight(0.8f),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                StrengthRow()
            }
            item {
                DexterityRow()
            }
            item {
                VitalityRow()
            }
            item {
                InsightRow()
            }
            /*item(span = { GridItemSpan(2) }) {
                ActionButton(modifier = Modifier.fillMaxWidth(), onClick = { }) {
                    Text(text = stringResource(id = R.string.skills_other_skill))
                }
            }*/
        }
        var buttonIndex by remember { mutableStateOf(0) }
        var openDialog by remember { mutableStateOf(false) }
        if (openDialog) {
            Dialog(onDismissRequest = { openDialog = false }) {
                when (buttonIndex) {
                    0 -> FightDialog()
                    1 -> SurvivalDialog()
                    2 -> ContestDialog()
                    3 -> KnowledgeDialog()
                }
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.weight(0.2f),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val buttons = listOf(
                R.string.skills_fight,
                R.string.skills_survival,
                R.string.skills_contest,
                R.string.skills_knowledge
            )
            items(4) { index ->
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
}

@Composable
fun StrengthRow() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        var check1 by remember { mutableStateOf(trainer.strength) }
        var check2 by remember { mutableStateOf(trainer.tough) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_strength))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check1,
                        onCheckedChange = {
                            trainer.strength = i + if(it) 1 else 0
                            check1 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_tough))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check2,
                        onCheckedChange = {
                            trainer.tough = i + if(it) 1 else 0
                            check2 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DexterityRow() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        var check1 by remember { mutableStateOf(trainer.dexterity) }
        var check2 by remember { mutableStateOf(trainer.cool) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_dexterity))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check1,
                        onCheckedChange = {
                            trainer.dexterity = i + if(it) 1 else 0
                            check1 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_cool))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check2,
                        onCheckedChange = {
                            trainer.cool = i + if(it) 1 else 0
                            check2 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VitalityRow() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        var check1 by remember { mutableStateOf(trainer.vitality) }
        var check2 by remember { mutableStateOf(trainer.beauty) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_vitality))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check1,
                        onCheckedChange = {
                            trainer.vitality = i + if(it) 1 else 0
                            check1 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_beauty))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check2,
                        onCheckedChange = {
                            trainer.beauty = i + if(it) 1 else 0
                            check2 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InsightRow() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        var check1 by remember { mutableStateOf(trainer.insight) }
        var check2 by remember { mutableStateOf(trainer.intelligence) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_insight))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check1,
                        onCheckedChange = {
                            trainer.insight = i + if(it) 1 else 0
                            check1 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(id = R.string.skills_intelligence))
            Row {
                for (i in 0 until checkboxNumber) {
                    MyCheckBox(
                        checked = i < check2,
                        onCheckedChange = {
                            trainer.intelligence = i + if(it) 1 else 0
                            check2 = i + if(it) 1 else 0
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FightDialog() {
    var check1 by remember { mutableStateOf(trainer.fight) }
    var check2 by remember { mutableStateOf(trainer.brawl) }
    var check3 by remember { mutableStateOf(trainer.tThrow) }
    var check4 by remember { mutableStateOf(trainer.evasion) }
    var check5 by remember { mutableStateOf(trainer.weapons) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.skills_fight))

        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check1,
                    onCheckedChange = {
                        trainer.fight = i + if(it) 1 else 0
                        check1 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_brawl))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check2,
                    onCheckedChange = {
                        trainer.brawl = i + if(it) 1 else 0
                        check2 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_throw))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check3,
                    onCheckedChange = {
                        trainer.tThrow = i + if(it) 1 else 0
                        check3 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_evasion))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check4,
                    onCheckedChange = {
                        trainer.evasion = i + if(it) 1 else 0
                        check4 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_weapons))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check5,
                    onCheckedChange = {
                        trainer.weapons = i + if(it) 1 else 0
                        check5 = i + if(it) 1 else 0
                    }
                )
            }
        }
    }
}

@Composable
fun SurvivalDialog() {
    var check1 by remember { mutableStateOf(trainer.survival) }
    var check2 by remember { mutableStateOf(trainer.alert) }
    var check3 by remember { mutableStateOf(trainer.atheletic) }
    var check4 by remember { mutableStateOf(trainer.natural) }
    var check5 by remember { mutableStateOf(trainer.stealth) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.skills_survival))

        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check1,
                    onCheckedChange = {
                        trainer.survival = i + if(it) 1 else 0
                        check1 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_alert))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check2,
                    onCheckedChange = {
                        trainer.alert = i + if(it) 1 else 0
                        check2 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_athletic))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check3,
                    onCheckedChange = {
                        trainer.atheletic = i + if(it) 1 else 0
                        check3 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_nature))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check4,
                    onCheckedChange = {
                        trainer.natural = i + if(it) 1 else 0
                        check4 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_stealth))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check5,
                    onCheckedChange = {
                        trainer.stealth = i + if(it) 1 else 0
                        check5 = i + if(it) 1 else 0
                    }
                )
            }
        }
    }
}

@Composable
fun ContestDialog() {
    var check1 by remember { mutableStateOf(trainer.contest) }
    var check2 by remember { mutableStateOf(trainer.empathy) }
    var check3 by remember { mutableStateOf(trainer.etiquette) }
    var check4 by remember { mutableStateOf(trainer.intimidate) }
    var check5 by remember { mutableStateOf(trainer.perform) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.skills_contest))

        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check1,
                    onCheckedChange = {
                        trainer.contest = i + if(it) 1 else 0
                        check1 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_empathy))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check2,
                    onCheckedChange = {
                        trainer.empathy = i + if(it) 1 else 0
                        check2 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_etiquette))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check3,
                    onCheckedChange = {
                        trainer.etiquette = i + if(it) 1 else 0
                        check3 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_intimidate))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check4,
                    onCheckedChange = {
                        trainer.intimidate = i + if(it) 1 else 0
                        check4 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_perform))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check5,
                    onCheckedChange = {
                        trainer.perform = i + if(it) 1 else 0
                        check5 = i + if(it) 1 else 0
                    }
                )
            }
        }
    }
}

@Composable
fun KnowledgeDialog() {
    var check1 by remember { mutableStateOf(trainer.knowledge) }
    var check2 by remember { mutableStateOf(trainer.crafts) }
    var check3 by remember { mutableStateOf(trainer.lore) }
    var check4 by remember { mutableStateOf(trainer.medicine) }
    var check5 by remember { mutableStateOf(trainer.science) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.skills_knowledge))

        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check1,
                    onCheckedChange = {
                        trainer.knowledge = i + if(it) 1 else 0
                        check1 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_crafts))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check2,
                    onCheckedChange = {
                        trainer.crafts = i + if(it) 1 else 0
                        check2 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_lore))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check3,
                    onCheckedChange = {
                        trainer.lore = i + if(it) 1 else 0
                        check3 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_medicine))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check4,
                    onCheckedChange = {
                        trainer.medicine = i + if(it) 1 else 0
                        check4 = i + if(it) 1 else 0
                    }
                )
            }
        }
        Text(text = stringResource(id = R.string.skills_science))
        Row {
            for (i in 0 until checkboxNumber) {
                MyCheckBox(
                    checked = i < check5,
                    onCheckedChange = {
                        trainer.science = i + if(it) 1 else 0
                        check5 = i + if(it) 1 else 0
                    }
                )
            }
        }
    }
}