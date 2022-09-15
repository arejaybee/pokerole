package com.arejaybee.pokerole_core_sheet

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.arejaybee.pokerole_core_sheet.pokemon.Pokemon
import com.arejaybee.pokerole_core_sheet.trainer.Trainer
import com.arejaybee.pokerole_core_sheet.views.MainContentView
import com.arejaybee.pokerole_core_sheet.views.PokemonContentView
import com.arejaybee.pokerole_core_sheet.views.context

class MainActivity : AppCompatActivity() {

    val imageUriState = mutableStateOf<Uri?>(null)
    val activityPageFlag = mutableStateOf<Pokemon?>(null)

    val selectImageLauncher = registerForActivityResult(GetContent()) {
        imageUriState.value = it
        trainer.profilePicture = it.toString()
    }
    lateinit var trainer: Trainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainer = try {
            Trainer.loadData(this)
        } catch (e: Exception) {
            Trainer(this)
        }
        imageUriState.value = if(trainer.profilePicture.isEmpty()) null else Uri.parse(trainer.profilePicture)
        setContent {
            activityPageFlag.value?.let {
                PokemonContentView(this, it)
            }?: MainContentView(this)
        }
    }

    fun GoToPokemonView(pokemon: Pokemon) {
        context.activityPageFlag.value = pokemon
    }
}