package com.example.arinteriordesigner.domain.model

import com.example.arinteriordesigner.data.entity.ProjectEntity

data class ProjectModel(
    val id: String,
    val name: String,
    val createdAt: Long,
    val updatedAt: Long,
    val roomMeshRef: String?,
    val anchors: List<AnchorPose>?
)

fun ProjectEntity.toModel() = ProjectModel(
    id,
    name,
    createdAt,
    updatedAt,
    roomMeshRef,
    anchors
)
