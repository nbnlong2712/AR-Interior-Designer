package com.example.arinteriordesigner.presenter.ui.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arinteriordesigner.domain.viewmodel.CreateProjectViewModel

@Composable
fun CreateProjectDialog(
    viewModel: CreateProjectViewModel,
    onDismiss: () -> Unit,
    onSaved: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Project") },
        text = {
            Column(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    label = { Text("Name *") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                viewModel.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            }
        },
        confirmButton = {
            val saving = viewModel.saving
            TextButton(onClick = { viewModel.save { onSaved(); onDismiss() } }, enabled = !saving) {
                Text(
                    if (saving) "Saving..." else "Save"
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}