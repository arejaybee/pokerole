package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.Utility
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM
import com.arejaybee.pokerole_core_sheet.trainer.Potion
import com.arejaybee.pokerole_core_sheet.views.trainer

@Composable
fun Bag(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(0.8f)) {
            Column(modifier = Modifier.weight(1f)) {
                PotionPocket(Modifier.fillMaxSize())
            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                PocketButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Main Pocket")
                }
                PocketButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .fillMaxWidth()
                ) {
                Text(text = "Small Pocket")
            }
            }
        }
        Row(modifier = Modifier
            .weight(0.2f)
            .padding(horizontal = 30.dp)) {
            BadgeCase()
        }
    }
}

@Composable
fun PocketButton(modifier: Modifier, content: @Composable RowScope.() -> Unit) {
    val openDialog = remember { mutableStateOf(false) }
    if(openDialog.value) {
        Dialog(onDismissRequest = {openDialog.value = false}) {
            Box(modifier = Modifier
                .size(Utility.getDW(2f), Utility.getDH(0.1f))
                .background(Color.White))
        }
    }
    Button(
        modifier = modifier,
        onClick = {
            openDialog.value = !openDialog.value
        },
        content = content
    )
}

@Composable
fun PotionPocket(modifier: Modifier) {
    Column(modifier = modifier.fillMaxHeight(), Arrangement.SpaceBetween) {
        PotionRow(modifier = Modifier.weight(1f), potionName = POTION_ENUM.Potion)
        PotionRow(modifier = Modifier.weight(1f), potionName = POTION_ENUM.Super_Potion)
        PotionRow(modifier = Modifier.weight(1f), potionName = POTION_ENUM.Hyper_Potion)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PotionRow(modifier: Modifier, potionName: POTION_ENUM) {
    val potionType = Potion.getPotionByType(potionName)
    var potionCount by remember { mutableStateOf( trainer.bag.getPotionList(potionName).size.toString()) }
    var buttonPress by remember { mutableStateOf(true)}
    var potionUses by remember { mutableStateOf(trainer.bag.getPotionList(potionName).firstOrNull()?.uses?:0) }
    Row(modifier = modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically)
    {
        Button(modifier = Modifier.weight(0.25f), onClick = {
            val potion =  trainer.bag.getPotionList(potionName).firstOrNull()
            potion?.let {
                potion.uses--
                potionUses = potion.uses
                if(potion.uses == 0) {
                    trainer.bag.popFirstPotion(potionName)
                }
                potionCount = trainer.bag.getPotionList(potionName).size.toString()
            }
            potionUses = if (trainer.bag.getPotionList(potionName).isEmpty()) 0
                        else trainer.bag.getPotionList(potionName).firstOrNull()?.uses?:potionType.maxUses
            buttonPress = !buttonPress
        }) {
            Text(textAlign = TextAlign.Center, text = potionName.name.replace("_", " "))
        }
        TextField(
            modifier = Modifier
                .weight(0.2f)
                .padding(5.dp),
            value = potionCount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                val count = it
                try {
                    val newListSize = Integer.parseInt(count)
                    val topPotionUses =  trainer.bag.getPotionList(potionName).firstOrNull()?.uses ?: potionType.maxUses
                    trainer.bag.clearPotions(potionName)
                    for (i in 0 until newListSize) {
                        trainer.bag.addPotion(potionName)
                    }
                    if ( trainer.bag.getPotionList(potionName).size > 0) {
                        trainer.bag.getPotionList(potionName).first().uses = topPotionUses
                    }
                } catch (ex: NumberFormatException) {

                }
                potionCount = count
                potionUses = if (trainer.bag.getPotionList(potionName).isEmpty()) 0
                            else trainer.bag.getPotionList(potionName).firstOrNull()?.uses?:potionType.maxUses
            })
        val maxUses = 7.coerceAtMost(potionType.maxUses)
        Column(modifier = Modifier.weight(0.55f), horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Start) {
                for (i in 0 until maxUses) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        Checkbox(
                            checked = i < potionUses,
                            onCheckedChange = {},
                            enabled = false
                        )
                    }
                }
            }
            if (potionType.maxUses > 7) {
                Row(horizontalArrangement = Arrangement.Start) {
                    for (i in 0 until 7) {
                        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                            Checkbox(
                                checked = i+7 < potionUses,
                                onCheckedChange = {},
                                enabled = false
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier)
            }
        }
    }
}

@Composable
fun BadgeCase() {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Badges: ")
       for (i in 0 until 8) {
           Image(painter = painterResource(id = R.drawable.ic_pokeball), contentDescription = "Player Badge")

        }
    }
}