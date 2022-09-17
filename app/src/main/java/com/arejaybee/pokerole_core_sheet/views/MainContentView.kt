package com.arejaybee.pokerole_core_sheet.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.arejaybee.pokerole_core_sheet.R.drawable
import com.arejaybee.pokerole_core_sheet.R.string
import com.arejaybee.pokerole_core_sheet.views.PokemonPage.POKEMON
import com.arejaybee.pokerole_core_sheet.views.trainer.Bag
import com.arejaybee.pokerole_core_sheet.views.trainer.TrainerCard
import com.arejaybee.pokerole_core_sheet.views.trainer.TrainerSkill

enum class Page { TRAINER_CARD, SKILLS, BAG, POKEMON }

lateinit var context: MainActivity

var selectedPage by mutableStateOf(Page.TRAINER_CARD)
val roundedMod = Modifier.clip(RoundedCornerShape(25, 25, 25, 25))

@Composable
fun MainContentView(newContext: MainActivity) {
    context = newContext
    Box(Modifier.background(colorResource(id = R.color.background_primary)).fillMaxSize()) {
        Column(Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),) {
            Row(Modifier.weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                NavButtons(modifier = Modifier.weight(0.15f), selectedPage)
                val pageModifier = Modifier.weight(0.85f)
                when (selectedPage) {
                    Page.TRAINER_CARD -> TrainerCard(modifier = pageModifier)
                    Page.SKILLS -> TrainerSkill(modifier = pageModifier)
                    Page.BAG -> Bag(modifier = pageModifier)
                    else -> Text("")
                }
            }
            PokemonGrid(modifier = Modifier.weight(0.2f))
        }
    }
}


@Composable
fun NavButtons(modifier: Modifier = Modifier, page: Page) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = { selectedPage = Page.TRAINER_CARD }, color = if (page == Page.TRAINER_CARD) R.color.my_button_secondary else R.color.button_primary) {
            Text(stringResource(id = string.nav_trainer_card), textAlign = TextAlign.Center)
        }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = { selectedPage = Page.SKILLS }, color = if (page == Page.SKILLS) R.color.my_button_secondary else R.color.button_primary) { Text(stringResource(id = string.nav_trainer_skills), textAlign = TextAlign.Center) }
        ActionButton(modifier = roundedMod.fillMaxWidth(), onClick = {
                selectedPage = Page.BAG
            }, color = if (page == Page.BAG) R.color.my_button_secondary else R.color.button_primary) { Text(stringResource(id = string.nav_trainer_bag), textAlign = TextAlign.Center) }
    }
}

@Composable
fun PokemonGrid(modifier: Modifier) {
    Row(modifier.fillMaxSize()
        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        context.trainer.value.pokemon.forEach { pokemon ->
            GlideImage(
                pokemon.profilePicture,
                drawable.ic_pokeball,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectedPokemonPage = POKEMON
                        context.goToPokemonView(pokemon)
                    }
            )
        }
    }
}