package com.example.arinteriordesigner.domain.model

import com.example.arinteriordesigner.data.entity.ProjectEntity

data class ProjectModel(
    val id: String,
    val name: String,
    val description: String?,
    val thumbnailUri: String?,
    val lastOpenedAt: Long
)

fun ProjectEntity.toModel() = ProjectModel(uid, name, description, thumbnailUri, lastOpenedAt)
