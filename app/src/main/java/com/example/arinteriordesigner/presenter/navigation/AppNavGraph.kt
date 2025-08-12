package com.example.arinteriordesigner.presenter.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.arinteriordesigner.core.utils.Configs
import com.example.arinteriordesigner.core.utils.Route
import com.example.arinteriordesigner.presenter.ui.auth.authNav
import com.example.arinteriordesigner.presenter.ui.home.homeNav
import com.example.arinteriordesigner.presenter.ui.onboard.onboardNav
import com.example.arinteriordesigner.presenter.ui.splash.splashNav

@Composable
fun AppNavGraph(startNav: String = Route.SPLASH, context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startNav) {
        splashNav(controller = navController, context = context)
        onboardNav(controller = navController)
        homeNav(controller = navController)
        authNav(controller = navController)
    }
}