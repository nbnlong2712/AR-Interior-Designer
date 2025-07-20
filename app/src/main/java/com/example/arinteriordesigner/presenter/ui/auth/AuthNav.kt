package com.example.arinteriordesigner.presenter.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.arinteriordesigner.core.base.navigateScreen
import com.example.arinteriordesigner.core.utils.Route

fun NavGraphBuilder.authNav(controller: NavHostController) {
    navigateScreen(route = Route.AUTH, content = { AuthScreen() })
}
