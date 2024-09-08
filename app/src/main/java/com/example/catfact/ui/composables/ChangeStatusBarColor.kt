package com.example.catfact.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

@Composable
fun ChangeStatusBarColor(color: Color) {
    val context = LocalContext.current
    val view = LocalView.current
    val window = (context as? androidx.activity.ComponentActivity)?.window ?: return
    window.statusBarColor = color.toArgb()

}