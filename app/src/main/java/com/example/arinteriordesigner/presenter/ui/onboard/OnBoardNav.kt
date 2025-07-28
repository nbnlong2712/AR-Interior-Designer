package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.arinteriordesigner.core.base.navigateScreen
import com.example.arinteriordesigner.core.utils.Route

fun NavGraphBuilder.onboardNav(controller: NavHostController) {
    navigateScreen(route = Route.ONBOARD, content = {
        OnBoardScreen(pages = OnBoardPage.onBoardPages, onFinish = {
            controller.navigate(Route.AUTH) {
                popUpTo(Route.ONBOARD) { inclusive = true }
            }
        })
    })
}