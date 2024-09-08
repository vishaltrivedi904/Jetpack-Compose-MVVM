package com.example.catfact.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.ui.theme.PrimaryColor
import com.example.catfact.ui.theme.fontFamily

@Composable
fun BreedItem(breed: Data, onClick: (Data) -> Unit) {

    val textStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(PrimaryColor)
            .clickable { onClick(breed) }
    ) {
        Text(
            text = breed.breed,
            modifier = Modifier.padding(16.dp),
            style = textStyle
        )
    }
}