package com.example.arinteriordesigner.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.arinteriordesigner.presenter.ui.auth.authNav
import com.example.arinteriordesigner.presenter.ui.home.homeNav
import com.example.arinteriordesigner.presenter.ui.onboard.onboardNav

@Composable
fun AppNavGraph(startNav: String = "onboard") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startNav) {
        onboardNav(controller = navController)
        homeNav(controller = navController)
        authNav(controller = navController)
    }
}