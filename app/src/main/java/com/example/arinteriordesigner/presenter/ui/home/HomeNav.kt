package com.example.arinteriordesigner.presenter.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.arinteriordesigner.core.utils.Route

fun NavGraphBuilder.homeNav(controller: NavHostController) {
    composable(route = Route.HOME) {
        HomeScreen()
    }
}