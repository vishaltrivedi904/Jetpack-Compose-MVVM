package com.example.catfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.catfact.navigation.AppRouter
import com.example.catfact.ui.theme.PrimaryColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRouter()
        }
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        window.statusBarColor = PrimaryColor.toArgb()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppRouter()
}