package com.arejaybee.pokerole_core_sheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arejaybee.pokerole_core_sheet.trainer.Trainer
import com.arejaybee.pokerole_core_sheet.views.MainContentView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trainer = try {
            Trainer.loadData(this)
        } catch (e: Exception) {
            Trainer(this)
        }
        setContent {
            MainContentView(trainer)
        }
    }


}