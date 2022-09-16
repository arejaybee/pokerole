package com.arejaybee.pokerole_core_sheet.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arejaybee.pokerole_core_sheet.MainActivity
import com.arejaybee.pokerole_core_sheet.R
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.views.trainer.Bag
import com.arejaybee.pokerole_core_sheet.views.trainer.TrainerSkill
import com.arejaybee.pokerole_core_sheet.views.trainer.TrainerCard

enum class Page { TRAINER_CARD, SKILLS, BAG, POKEMON }
lateinit var context: MainActivity

var selectedPage by mutableStateOf(Page.TRAINER_CARD)
val roundedMod = Modifier.clip(RoundedCornerShape(25, 25, 25, 25))

@Composable
fun MainContentView(newContext: MainActivity) {
    context = newContext
    Box(Modifier.background(colorResource(id = R.color.background_primary))) {
        Column(Modifier.padding(10.dp)) {
            Row(Modifier.weight(0.8f)) {
                NavButtons(modifier = Modifier.weight(0.15f))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                val pageModifier = Modifier.weight(0.85f)
                when (selectedPage) {
                    Page.TRAINER_CARD -> TrainerCard(modifier = pageModifier)
                    Page.SKILLS -> TrainerSkill(modifier = pageModifier)
                    Page.BAG -> Bag(modifier = pageModifier)
                    else -> Text("")
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            PokemonGrid(modifier = Modifier.weight(0.2f))
        }
    }
}


@Composable
fun NavButtons(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPage = Page.TRAINER_CARD
        }) {
            Text(stringResource(id = string.nav_trainer_card), textAlign = TextAlign.Center)
        }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPage = Page.SKILLS
        }) {
            Text(stringResource(id = string.nav_trainer_skills), textAlign = TextAlign.Center)
        }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
            selectedPage = Page.BAG
        }) {
            Text(stringResource(id = string.nav_trainer_bag), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun PokemonGrid(modifier: Modifier) {
    Row(modifier = modifier) {
        context.trainer.value.pokemon.forEach { pokemon ->
            GlideImage(
                pokemon.profilePicture,
                R.drawable.ic_pokeball,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        context.goToPokemonView(pokemon)
                    }
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}