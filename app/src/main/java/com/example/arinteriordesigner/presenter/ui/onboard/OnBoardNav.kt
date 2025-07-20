package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.arinteriordesigner.core.utils.Route

fun NavGraphBuilder.onboardNav(controller: NavHostController) {
    composable(route = Route.ONBOARD) {
        OnBoardScreen(pages = OnBoardPage.onBoardPages, onFinish = {
            controller.navigate(Route.AUTH) {
                popUpTo(Route.ONBOARD) { inclusive = true }
            }
        })
    }
}