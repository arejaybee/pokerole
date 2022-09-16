package com.arejaybee.pokerole_core_sheet.views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.pokemon.PokemonType
import com.arejaybee.pokerole_core_sheet.trainer.Nature
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.PokemonSurvival
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerContest
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerFight
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerKnowledge
import com.arejaybee.pokerole_core_sheet.views.SkillDialogType.TrainerSurvival
import com.skydoves.landscapist.ImageOptions

@Composable
fun ActionButton(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_primary))
    ) {
        content()
    }
}

@Composable
fun GlideImage(uriString: String?, defaultImage: Int, modifier: Modifier = Modifier) {
    val uri: Uri? =
        if (uriString == null || uriString == "null" || uriString.isBlank()) null else Uri.parse(
            uriString
        )
    GlideImage(uri, defaultImage, modifier)
}

@Composable
fun GlideImage(uri: Uri?, defaultImage: Int, modifier: Modifier = Modifier) {
    uri?.let {
        com.skydoves.landscapist.glide.GlideImage(imageModel = it, modifier = modifier, imageOptions = ImageOptions(contentScale = ContentScale.FillHeight))
    } ?: run {
        Image(
            painter = painterResource(id = defaultImage),
            contentDescription = "",
            modifier = modifier
        )
    }
}

@Composable
fun LabeledButton(modifier: Modifier, label: String, onClick: () -> Unit, content: @Composable BoxScope.() -> Unit) {
    Column(
        roundedMod
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            )
            .clickable(onClick = onClick)
            .background(color = colorResource(id = R.color.button_primary))
            .then(modifier)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp),
            fontSize = 12.sp
        )
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun <T> Dropdown(modifier: Modifier, selection: MutableState<String>, list: Array<T>, expanded: MutableState<Boolean>, onSelect: (selection: T) -> Unit) {
    DropdownMenu(
        modifier = modifier.then(
            Modifier
                .fillMaxHeight()
                .clickable { expanded.value = true }
        ),
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }) {
        list.forEach { item ->
            DropdownMenuItem(
                onClick = {
                    onSelect(item)
                    expanded.value = false
                    selection.value = item.toString()
                }
            ) {
                Text(item.toString())
            }
        }
    }
}

@Composable
fun NatureDropdown(modifier: Modifier = Modifier, selection: String, onSelect: (selection: Nature) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val boxName = remember { mutableStateOf(selection) }
    LabeledButton(
        modifier = modifier,
        label = stringResource(id = R.string.dropdown_label_nature),
        onClick = {
            expanded.value = true
        },
        content = {
            Text(
                text = boxName.value,
                textAlign = TextAlign.Center
            )
            Dropdown(
                modifier = Modifier.align(Alignment.Center),
                selection = boxName,
                list = Nature.values(),
                expanded = expanded,
                onSelect = onSelect
            )
        }
    )
}

@Composable
fun TypeDropdown(modifier: Modifier = Modifier, selection: String, onSelect: (selection: PokemonType) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val boxName = remember { mutableStateOf(selection) }
    LabeledButton(
        modifier = modifier,
        onClick = {
            expanded.value = true
        },
        label = stringResource(id = R.string.dropdown_label_type),
        content = {
            Text(
                text = boxName.value,
                textAlign = TextAlign.Center
            )
            Dropdown(
                modifier = Modifier.align(Alignment.Center),
                selection = boxName,
                list = PokemonType.values(),
                expanded = expanded,
                onSelect = onSelect
            )
        }
    )
}


enum class SkillDialogType { TrainerFight, TrainerSurvival, TrainerContest, TrainerKnowledge, PokemonFight, PokemonSurvival, PokemonContest }
@Composable
fun AttributeDialog(type: SkillDialogType) {
    val aList: MutableList<Int> = when(type) {
        TrainerFight -> listOf(context.trainer.value.fight, context.trainer.value.brawl, context.trainer.value.tThrow, context.trainer.value.evasion, context.trainer.value.weapons)
        TrainerSurvival -> listOf(context.trainer.value.survival, context.trainer.value.alert, context.trainer.value.atheletic, context.trainer.value.natural, context.trainer.value.stealth)
        TrainerContest -> listOf(context.trainer.value.contest, context.trainer.value.empathy, context.trainer.value.etiquette, context.trainer.value.intimidate, context.trainer.value.perform)
        TrainerKnowledge -> listOf(context.trainer.value.knowledge, context.trainer.value.crafts, context.trainer.value.lore, context.trainer.value.medicine, context.trainer.value.science)
        PokemonFight -> listOf(context.selectedPokemon.value!!.fight, context.selectedPokemon.value!!.brawl, context.selectedPokemon.value!!.channel, context.selectedPokemon.value!!.evasion, context.selectedPokemon.value!!.clash)
        PokemonSurvival -> listOf(context.selectedPokemon.value!!.survival, context.selectedPokemon.value!!.alert, context.selectedPokemon.value!!.atheletic, context.selectedPokemon.value!!.natural, context.selectedPokemon.value!!.stealth)
        PokemonContest -> listOf(context.selectedPokemon.value!!.contest, context.selectedPokemon.value!!.allure, context.selectedPokemon.value!!.etiquette, context.selectedPokemon.value!!.intimidate, context.selectedPokemon.value!!.perform)
    }.toMutableList()
    val stringList: List<Int> = when(type) {
        TrainerFight -> listOf(R.string.skills_fight, R.string.skills_brawl, R.string.skills_throw, R.string.skills_evasion, R.string.skills_weapons)
        TrainerSurvival -> listOf(R.string.skills_survival, R.string.skills_alert, R.string.skills_athletic, R.string.skills_nature, R.string.skills_stealth)
        TrainerContest -> listOf(R.string.skills_contest, R.string.skills_empathy, R.string.skills_etiquette, R.string.skills_intimidate, R.string.skills_perform)
        TrainerKnowledge -> listOf(R.string.skills_knowledge, R.string.skills_crafts, R.string.skills_lore, R.string.skills_medicine, R.string.skills_science)
        PokemonFight -> listOf(R.string.skills_fight, R.string.skills_brawl, R.string.skills_channel, R.string.skills_evasion, R.string.skills_clash)
        PokemonSurvival -> listOf(R.string.skills_survival, R.string.skills_alert, R.string.skills_athletic, R.string.skills_nature, R.string.skills_stealth)
        PokemonContest -> listOf(R.string.skills_contest, R.string.skills_allure, R.string.skills_etiquette, R.string.skills_intimidate, R.string.skills_perform)
    }
    val inputList: List<MutableState<String>> = listOf(
        remember { mutableStateOf(aList[0].toString()) },
        remember { mutableStateOf(aList[1].toString()) },
        remember { mutableStateOf(aList[2].toString()) },
        remember { mutableStateOf(aList[3].toString()) },
        remember { mutableStateOf(aList[4].toString()) }
    )
    Column(
        Modifier
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DialogSkillTextField(0, stringList, inputList, aList, type)
        LazyVerticalGrid(
            columns = Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            items(4) { index ->
                DialogSkillTextField(index + 1, stringList, inputList, aList, type)
            }

        }
    }
}

@Composable
fun DialogSkillTextField(index:Int, stringList: List<Int>, inputList: List<MutableState<String>>, aList: MutableList<Int>, type: SkillDialogType) {
    TextField(
        modifier = roundedMod,
        label = { Text(stringResource(id = stringList[index])) },
        value = inputList[index].value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            try {
                aList[index] = Integer.parseInt(it)
                inputList[index].value = aList[index].toString()
                if(type.name.contains("Trainer")) {
                    context.trainer.value.updateSkills(aList, type)
                } else {
                    context.selectedPokemon.value!!.updateSkills(aList, type)
                }
            } catch (e: Exception) {
                inputList[index].value = ""
            }
        })
}