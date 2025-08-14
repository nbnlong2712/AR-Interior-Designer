package com.example.arinteriordesigner.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arinteriordesigner.domain.model.ProjectModel
import com.example.arinteriordesigner.domain.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var saving by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun save(onSave: () -> Unit) {
        val n = name.trim()
        if (n.isEmpty()) {
            error = "Name is required"; return
        }
        saving = true
        viewModelScope.launch {
            try {
                projectRepository.upsert(
                    ProjectModel(
                        UUID.randomUUID().toString(),
                        name,
                        System.currentTimeMillis(),
                        System.currentTimeMillis(),
                        "",
                        emptyList()
                    )
                )
                onSave()
            } catch (t: Throwable) {
                error = t.message ?: "Unknown error"
            } finally {
                saving = false
            }
        }
    }
}