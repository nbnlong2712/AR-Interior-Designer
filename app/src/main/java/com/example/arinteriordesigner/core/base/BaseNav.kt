package com.example.arinteriordesigner.core.base

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 *  Navigate màn hình không có argument.
 */
inline fun NavGraphBuilder.navigateScreen(
    route: String,
    crossinline content: @Composable () -> Unit
) {
    composable(route) {
        content()
    }
}

/**
 * Navigate màn hình có argument.
 */
inline fun NavGraphBuilder.navigateScreenWithArgs(
    route: String,
    args: List<NamedNavArgument> = emptyList(),
    deepLink: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = args,
        deepLinks = deepLink
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

/**
 * Navigate có hỗ trợ chuyển cảnh (transition) – nếu dùng thư viện hỗ trợ như Accompanist hoặc AnimationNavHost.
 * Mặc định Jetpack Navigation chưa hỗ trợ full animation.
 */
inline fun NavGraphBuilder.registerScreenWithTransition(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    crossinline content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

/**
 * Navigation nếu bạn muốn inject luôn NavController vào Composable.
 * Hữu ích trong app nhỏ, hoặc khi không muốn pass thủ công.
 */
inline fun NavGraphBuilder.registerScreenWithNavController(
    navController: NavController,
    route: String,
    crossinline content: @Composable (NavController) -> Unit
) {
    composable(route = route) {
        content(navController)
    }
}