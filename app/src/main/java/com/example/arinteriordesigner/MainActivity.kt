package com.example.arinteriordesigner

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.arinteriordesigner.data.repositoryimpl.AuthRepositoryImpl
import com.example.arinteriordesigner.domain.repository.AuthRepository
import com.example.arinteriordesigner.presenter.navigation.AppNavGraph
import com.facebook.CallbackManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val callbackManager = CallbackManager.Factory.create()

    @Inject
    lateinit var authRepo: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyAppTheme {
                AppNavGraph()
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        (authRepo as AuthRepositoryImpl).callbackManager.onActivityResult(
            requestCode,
            resultCode,
            data
        )
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