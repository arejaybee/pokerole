package com.arejaybee.pokerole_core_sheet.views.trainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
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
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.Utility.MyCheckBox
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Hyper_Potion
import com.arejaybee.pokerole_core_sheet.trainer.POTION_ENUM.Super_Potion
import com.arejaybee.pokerole_core_sheet.trainer.Potion
import com.arejaybee.pokerole_core_sheet.views.ActionButton
import com.arejaybee.pokerole_core_sheet.views.GlideIcon
import com.arejaybee.pokerole_core_sheet.views.context

@Composable
fun Bag(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(modifier = Modifier.weight(0.9f), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {
            PotionPocket(Modifier.weight(0.7f))
            Column(
                modifier = Modifier.weight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
            ) {
                PocketActionButton(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    true
                ) {
                    Text(text = stringResource(id = string.bag_main_pocket))
                }
                PocketActionButton(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    false
                ) {
                    Text(text = stringResource(id = string.bag_small_pocket))
                }
                BadgeCase(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun PocketActionButton(
    modifier: Modifier,
    mainBag: Boolean,
    content: @Composable RowScope.() -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                val title = if (mainBag) R.string.bag_main_pocket else R.string.bag_small_pocket
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = stringResource(id = title),
                    textAlign = TextAlign.Center
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val list = if (mainBag) context.trainer.value.bag.mainItems else context.trainer.value.bag.battleItems
                    itemsIndexed(list) { index, item ->
                        var inputText by remember { mutableStateOf(item) }
                        TextField(
                            value = inputText,
                            modifier = Modifier.padding(10.dp),
                            onValueChange = {
                                if (mainBag) context.trainer.value.bag.mainItems[index] =
                                    it else context.trainer.value.bag.battleItems[index] = it
                                context.trainer.value.saveBagUpdates()
                                inputText = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
    ActionButton(
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
        PotionRow(modifier = Modifier.weight(1f), potionName = Super_Potion)
        PotionRow(modifier = Modifier.weight(1f), potionName = Hyper_Potion)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PotionRow(modifier: Modifier, potionName: POTION_ENUM) {
    val potionType = Potion.getPotionByType(context.trainer.value.bag, potionName)
    var potionCount by remember {
        mutableStateOf(
            context.trainer.value.bag.getPotionList(potionName).size.toString()
        )
    }
    var actionButtonPress by remember { mutableStateOf(true) }
    var potionUses by remember {
        mutableStateOf(
            context.trainer.value.bag.getPotionList(potionName).firstOrNull()?.uses ?: 0
        )
    }
    Row(modifier = modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly)
    {
        ActionButton(modifier = Modifier.weight(0.5f), onClick = {
            val potion = context.trainer.value.bag.getPotionList(potionName).firstOrNull()
            potion?.let {
                potion.uses--
                potionUses = potion.uses
                if (potion.uses == 0) {
                    context.trainer.value.bag.popFirstPotion(potionName)
                }
                potionCount = context.trainer.value.bag.getPotionList(potionName).size.toString()
            }
            potionUses = if (context.trainer.value.bag.getPotionList(potionName).isEmpty()) 0
            else context.trainer.value.bag.getPotionList(potionName).firstOrNull()?.uses ?: potionType.maxUses
            actionButtonPress = !actionButtonPress
        }) {
            Text(textAlign = TextAlign.Center, text = potionName.name.replace("_", " "))
        }
        TextField(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 5.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            shape = CircleShape,
            value = potionCount,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                val count = it
                try {
                    val newListSize = Integer.parseInt(count)
                    val topPotionUses = context.trainer.value.bag.getPotionList(potionName).firstOrNull()?.uses
                        ?: potionType.maxUses
                    context.trainer.value.bag.clearPotions(potionName)
                    for (i in 0 until newListSize) {
                        context.trainer.value.bag.addPotion(potionName)
                    }
                    if (context.trainer.value.bag.getPotionList(potionName).size > 0) {
                        context.trainer.value.bag.getPotionList(potionName).first().uses = topPotionUses
                    }
                } catch (ex: NumberFormatException) {

                }
                potionCount = count
                potionUses = if (context.trainer.value.bag.getPotionList(potionName).isEmpty()) 0
                else context.trainer.value.bag.getPotionList(potionName).firstOrNull()?.uses ?: potionType.maxUses
            })
        val maxUses = 7.coerceAtMost(potionType.maxUses)
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Start) {
                for (i in 0 until maxUses) {
                    MyCheckBox(
                        checked = i < potionUses,
                        onCheckedChange = {},
                        enabled = false
                    )
                }
            }
            if (potionType.maxUses > 7) {
                Row(horizontalArrangement = Arrangement.Start) {
                    for (i in 0 until 7) {
                        MyCheckBox(
                            checked = i + 7 < potionUses,
                            onCheckedChange = {},
                            enabled = false
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier)
            }
        }
    }
}

@Composable
fun BadgeCase(modifier: Modifier) {

    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                val title = string.bag_badge_case
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = stringResource(id = title),
                    textAlign = TextAlign.Center
                )
                Column(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly) {
                    Row(
                        modifier= Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        context.trainer.value.badges.subList(0,4).forEachIndexed { index, _ ->
                            BadgeIcon(modifier = Modifier.weight(1f), index)
                        }
                    }
                    Row(
                        Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        context.trainer.value.badges.subList(4,8).forEachIndexed { index, _ ->
                            BadgeIcon(modifier = Modifier.weight(1f), index+4)
                        }
                    }
                }
            }
        }
    }
    ActionButton(
        modifier = modifier,
        onClick = {
            openDialog.value = !openDialog.value
        }
    ) {
        Text(text = stringResource(id = string.bag_badge_case))
    }
}

@Composable
fun BadgeIcon(modifier: Modifier, index:Int) {
    IconButton(
        modifier = modifier.padding(end = 10.dp, bottom = 10.dp),
        onClick = {
            context.imageUtil.selectedBadge = index
            context.imageUtil.selectBadgeImage.launch("image/*")
        }
    ) {
        GlideIcon(context.imageUtil.badgeImageUri[index].value, Icons.Rounded.Lock)
    }
}