package com.example.arinteriordesigner.presenter.ui.splash

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.arinteriordesigner.core.base.navigateScreen
import com.example.arinteriordesigner.core.utils.Configs
import com.example.arinteriordesigner.core.utils.Route
import com.example.arinteriordesigner.data.local.prefs.PrefManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

fun NavGraphBuilder.splashNav(controller: NavHostController, context: Context) {
    navigateScreen(route = Route.SPLASH, content = {
        SplashScreen()
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val prefs = context.getSharedPreferences(Configs.PREF_APP_PREFS, Context.MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean(Configs.PREF_IS_FIRST_LAUNCH, true)

        LaunchedEffect(Unit) {
            delay(1000)
            when {
                currentUser != null -> {
                    controller.navigate(Route.HOME) {
                        popUpTo(Route.SPLASH) { inclusive = true }
                    }
                }

                isFirstLaunch -> {
                    controller.navigate(Route.ONBOARD) {
                        popUpTo(Route.SPLASH) { inclusive = true }
                    }
                    PrefManager.putBoolean(Configs.PREF_IS_FIRST_LAUNCH, false)
                }

                else -> {
                    controller.navigate(Route.AUTH) {
                        popUpTo(Route.SPLASH) { inclusive = true }
                    }
                }
            }
        }
    })
}