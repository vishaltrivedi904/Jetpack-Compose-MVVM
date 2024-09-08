package com.example.catfact.ui.screens.catdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.ui.composables.ChangeStatusBarColor
import com.example.catfact.ui.theme.PrimaryColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(navController: NavController, data: Data) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    ChangeStatusBarColor(color = PrimaryColor)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    val onBackPress: () -> Unit = {
        coroutineScope.launch {
            /*  Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show()*/
        }
        navController.navigateUp()
    }


    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(data.breed ?: "N/A") },
            navigationIcon = {
                IconButton(onClick = { onBackPress() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = PrimaryColor,
                titleContentColor = Color.Black,
            ),
            scrollBehavior = scrollBehavior,
        )
    }) { paddingValues ->


        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        )
        {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                data?.let {
                    Text(text = "Breed: ${data?.breed ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Coat: ${data?.coat ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Country: ${data?.country ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Origin: ${data?.origin ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Pattern: ${data?.pattern ?: "N/A"}")
                }

            }

        }
    }
}




