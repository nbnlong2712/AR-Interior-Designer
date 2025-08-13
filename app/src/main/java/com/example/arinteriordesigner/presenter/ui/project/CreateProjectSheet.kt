package com.example.arinteriordesigner.presenter.ui.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.arinteriordesigner.domain.viewmodel.CreateProjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectSheet(
    viewModel: CreateProjectViewModel,
    onDismiss: () -> Unit,
    onSaved: () -> Unit,
) {
    val fm = LocalFocusManager.current
    val saving = viewModel.saving
    val error = viewModel.error

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("New Project", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Name *") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { viewModel.description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            if (error != null) Text(error, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = onDismiss,
                    enabled = !saving,
                    modifier = Modifier.weight(1f)
                ) { Text("Cancel") }
                Button(
                    onClick = {
                        fm.clearFocus()
                        viewModel.save { onSaved() }
                        onDismiss()
                    },
                    enabled = !saving,
                    modifier = Modifier.weight(1f)
                ) { Text(if (saving) "Saving..." else "Save") }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}