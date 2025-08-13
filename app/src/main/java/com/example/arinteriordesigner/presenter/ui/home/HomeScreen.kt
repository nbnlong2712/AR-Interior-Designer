package com.example.arinteriordesigner.presenter.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.arinteriordesigner.domain.viewmodel.CreateProjectViewModel
import com.example.arinteriordesigner.domain.viewmodel.ProjectViewModel
import com.example.arinteriordesigner.presenter.ui.project.CreateProjectDialog


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ProjectViewModel = hiltViewModel(),
    createVm: CreateProjectViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    var showCreate by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreate = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        ProjectListScreen(
            state = state,
            {},
            { id ->
            }
        )
        Spacer(modifier = Modifier.padding(padding))
    }

    if (showCreate) {
        CreateProjectDialog(
            viewModel = createVm,
            onDismiss = {
                showCreate = false
            },
            onSaved = {
                Toast.makeText(
                    context,
                    if (createVm.saving) "Save success!!" else "Save error!!",
                    Toast.LENGTH_SHORT
                ).show()
                showCreate = false
            })
    }
}