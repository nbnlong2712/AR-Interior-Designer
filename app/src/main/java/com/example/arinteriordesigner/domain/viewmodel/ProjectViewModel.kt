package com.example.arinteriordesigner.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arinteriordesigner.domain.model.ProjectModel
import com.example.arinteriordesigner.domain.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

@HiltViewModel
class ProjectViewModel @Inject constructor(val projectRepository: ProjectRepository) : ViewModel() {
    private val _projectList = MutableStateFlow<List<ProjectModel>>(emptyList())

    private val _uiState = MutableStateFlow<UiState<List<ProjectModel>>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<ProjectModel>>> get() = _uiState

    init {
        loadProjectList()
    }

    fun loadProjectList() {
        viewModelScope.launch {
            projectRepository.getAllProjects()
                .onStart {
                    _uiState.value = UiState.Loading
                }
                .catch { it ->
                    _uiState.value = UiState.Error(it.message ?: "Error unknown")
                }
                .collect { list ->
                    _projectList.value = list
                    _uiState.value = UiState.Success(_projectList.value)
                }
        }
    }
}