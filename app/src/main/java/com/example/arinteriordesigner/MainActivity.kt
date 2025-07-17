package com.example.arinteriordesigner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.arinteriordesigner.presenter.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                AppNavGraph()
            }
        }
    }

    @Composable
    fun MyAppTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            colorScheme = lightColorScheme(),
            typography = Typography(),
            content = content
        )
    }
}