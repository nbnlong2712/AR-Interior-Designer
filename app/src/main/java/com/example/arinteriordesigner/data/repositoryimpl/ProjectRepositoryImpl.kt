package com.example.arinteriordesigner.data.repositoryimpl

import com.example.arinteriordesigner.data.dao.ProjectDao
import com.example.arinteriordesigner.data.entity.ProjectEntity
import com.example.arinteriordesigner.domain.model.ProjectModel
import com.example.arinteriordesigner.domain.model.toModel
import com.example.arinteriordesigner.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(val projectDao: ProjectDao) : ProjectRepository {
    override fun getAllProjects(): Flow<List<ProjectModel>> {
        return projectDao.getAllProjects().map { list ->
            list.map { it.toModel() }
        }
    }

    override fun getProjectById(id: String): Flow<ProjectModel> {
        return projectDao.getProjectById(id).map { it ->
            it.toModel()
        }
    }

    override suspend fun insert(project: ProjectModel) {
        val now = System.currentTimeMillis()
        projectDao.insert(
            ProjectEntity(
                uid = project.id,
                name = project.name,
                description = project.description,
                createdAt = now,
                updatedAt = now,
                thumbnailUri = project.thumbnailUri,
                scanId = null,
                lastOpenedAt = now
            )
        )
    }

    override suspend fun delete(id: String) {
        projectDao.delete(id)
    }

    override suspend fun update(project: ProjectModel) {
        val now = System.currentTimeMillis()
        projectDao.update(
            ProjectEntity(
                uid = project.id,
                name = project.name,
                description = project.description,
                createdAt = now,
                updatedAt = now,
                thumbnailUri = project.thumbnailUri,
                scanId = null,
                lastOpenedAt = now
            )
        )
    }
}