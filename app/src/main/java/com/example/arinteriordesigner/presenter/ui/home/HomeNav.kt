package com.example.arinteriordesigner.presenter.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.arinteriordesigner.core.base.navigateScreen
import com.example.arinteriordesigner.core.utils.Route

fun NavGraphBuilder.homeNav(controller: NavHostController) {
    navigateScreen(route = Route.HOME, content = { HomeScreen() })
}