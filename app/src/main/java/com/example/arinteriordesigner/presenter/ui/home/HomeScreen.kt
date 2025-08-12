package com.example.arinteriordesigner.presenter.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth

@Composable
@Preview()
fun HomeScreen() {
    val user = FirebaseAuth.getInstance().currentUser

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome " + user?.displayName, color = Color.Red)
            }
        }
    }
}