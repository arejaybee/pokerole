package com.arejaybee.pokerole_core_sheet.views

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.arejaybee.pokerole_core_sheet.R

@Composable
fun ActionButton(modifier: Modifier, onClick: () -> Unit, content: @Composable() (RowScope.() -> Unit)) {
   Button(
        modifier = modifier,
        onClick = onClick,
        colors =  ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_primary))
    ) {
        content()
    }
}