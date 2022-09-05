package com.arejaybee.pokerole_core_sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object Utility {

    @Composable
    fun getDW(percent: Float): Dp {
        return LocalConfiguration.current.screenWidthDp.dp.times(percent)
    }
    @Composable
    fun getDH(percent: Float): Dp {
        return LocalConfiguration.current.screenHeightDp.dp.times(percent)
    }
}