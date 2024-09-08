package com.example.catfact.ui.screens.assetsscreen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catfact.data.api.NetworkResponse
import com.example.catfact.data.model.catfact.FactResponse
import com.example.catfact.ui.composables.ChangeStatusBarColor
import com.example.catfact.ui.theme.PrimaryColor
import com.example.catfact.ui.theme.fontFamily
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactScreen(
    navController: NavController,
    mViewModel: FactViewModel,
) {
    val assets by mViewModel.assets.observeAsState(initial = NetworkResponse.Loading)

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val textStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    val onBackPress: () -> Unit = {
        coroutineScope.launch {
            /*Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show()*/
        }
        navController.navigateUp()
    }


    LaunchedEffect(Unit) {
        mViewModel.getAssetsById()
    }

    BackHandler {
        onBackPress()
    }

    ChangeStatusBarColor(color = PrimaryColor)
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Cat Breeds") },

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
            actions = {
                IconButton(onClick = { mViewModel.getAssetsById() }) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "Fact"
                    )
                }
            },
        )
    })
    { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        {

            when (assets) {
                is NetworkResponse.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Loading...", style = textStyle)
                    }
                }

                is NetworkResponse.Success -> {
                    val fact = (assets as NetworkResponse.Success<FactResponse>).data
                    assets?.let {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${fact.fact})",
                                style = textStyle
                            )
                        }
                    }
                }

                is NetworkResponse.Error -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: ${(assets as NetworkResponse.Error).message}",
                            style = textStyle
                        )
                    }


                }
            }
        }
    }


}