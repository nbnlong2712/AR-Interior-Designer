package com.example.arinteriordesigner.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arinteriordesigner.presenter.ui.home.HomeScreen
import com.example.arinteriordesigner.presenter.ui.onboard.OnBoardPage
import com.example.arinteriordesigner.presenter.ui.onboard.OnBoardScreen

@Composable
fun AppNavGraph(startNav: String = "onboard") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startNav) {
        composable("onboard") {
            OnBoardScreen(
                OnBoardPage.onBoardPages,
                onFinish = {
                    navController.navigate("home") {
                        popUpTo("onboard") { inclusive = true }
                    }
                },
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}