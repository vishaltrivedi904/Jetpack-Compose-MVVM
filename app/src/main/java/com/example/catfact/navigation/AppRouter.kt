package com.example.catfact.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.ui.screens.assetsscreen.FactScreen
import com.example.catfact.ui.screens.assetsscreen.FactViewModel
import com.example.catfact.ui.screens.catdetail.CatDetailScreen
import com.example.catfact.ui.screens.homescreen.HomeScreen
import com.example.catfact.ui.screens.homescreen.HomeViewModel
import com.google.gson.Gson

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController, viewModel)
        }
        composable("factScreen") { backStackEntry ->
            val viewModel: FactViewModel = hiltViewModel()
            FactScreen(navController, viewModel)
        }

        composable("catDetailScreen/{data}") { backStackEntry ->
            val jsonData = backStackEntry.arguments?.getString("data")
            val data = Gson().fromJson(jsonData, Data::class.java)
            data?.let {
                CatDetailScreen(navController, it)
            }
        }

    }

}