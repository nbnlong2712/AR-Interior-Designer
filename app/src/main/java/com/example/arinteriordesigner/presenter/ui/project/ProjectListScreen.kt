package com.example.arinteriordesigner.presenter.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.arinteriordesigner.domain.model.ProjectModel
import com.example.arinteriordesigner.domain.viewmodel.UiState

class ProjectListPreview : PreviewParameterProvider<ProjectModel> {
    override val values: Sequence<ProjectModel> = sequenceOf(
        ProjectModel(
            "001",
            "Scan your space with AR to capture the layout of your room",
            "hahahaha",
            "hahahaha",
            123123123
        ),
    )
}

@Composable
@PreviewScreenSizes
fun ForView(
    @PreviewParameter(ProjectListPreview::class) list: ProjectModel
) {
    val li = mutableListOf<ProjectModel>()
    li.add(list)
    ProjectListScreen(
        state = UiState.Success(li),
        {},
        {}
    )
}

@Composable
fun ProjectListScreen(
    state: UiState<List<ProjectModel>>,
    onCreateNew: () -> Unit,
    onOpenProject: (String) -> Unit
) {
    Scaffold { padding ->
        when (state) {
            is UiState.Loading -> Text("Loading...", modifier = Modifier.padding(padding))
            is UiState.Error -> Text(
                "Error: ${state.message}",
                modifier = Modifier.padding(padding)
            )

            is UiState.Success -> LazyColumn(contentPadding = padding) {
                items(
                    state.data,
                    key = { it.id }) { project ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onOpenProject(project.id) }
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(project.name, style = MaterialTheme.typography.titleMedium)
                            project.description?.let {
                                Text(
                                    it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}