package com.example.arinteriordesigner.domain.repository

import com.example.arinteriordesigner.domain.model.ProjectModel
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getAllProjects(): Flow<List<ProjectModel>>
    fun getProjectById(id: String): Flow<ProjectModel>
    suspend fun upsert(project: ProjectModel)
    suspend fun delete(id: String)
}