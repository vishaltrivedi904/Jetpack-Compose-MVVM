@file:Suppress("DEPRECATION")

package com.example.catfact.ui.screens.homescreen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.ui.composables.BreedItem
import com.example.catfact.ui.composables.ChangeStatusBarColor
import com.example.catfact.ui.theme.PrimaryColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, mViewModel: HomeViewModel) {
    val response: LazyPagingItems<Data> = mViewModel.breadResponse.collectAsLazyPagingItems()
    val isRefreshing = response.loadState.refresh is LoadState.Loading

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        if (response.itemCount == 0) {
            mViewModel.getBreeds()
        }
    }

    ChangeStatusBarColor(color = PrimaryColor)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Cat Breeds") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = PrimaryColor,
                titleContentColor = Color.Black,
            ),
            scrollBehavior = scrollBehavior,
            actions = {
                IconButton(onClick = { navController.navigate("factScreen") }) {
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "Fact"
                    )
                }
            },
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = {
                    response.refresh()
                }
            ) {

                LazyColumn(state = listState) {

                    items(response.itemCount) {
                        BreedItem(breed = response[it]!!, onClick = {
                            val dataJson = Uri.encode(Gson().toJson(it))
                            navController.navigate("catDetailScreen/$dataJson")
                        })
                    }

                    response.apply {
                        when {
                            loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                }
                            }

                            loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                                item {
                                    Text(text = "Error")
                                }
                            }

                            loadState.refresh is LoadState.NotLoading -> {
                            }
                        }
                    }
                }
            }
        }
    }
}


