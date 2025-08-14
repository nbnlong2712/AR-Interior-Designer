package com.example.arinteriordesigner.domain.model

import com.example.arinteriordesigner.data.entity.ScanEntity

data class ScanModel(
    val id: String,
    val projectId: String,
    val meshUrl: String?,
    val depthQuality: Int?,
    val notes: String?,
    val createdAt: Long
)

fun ScanEntity.toModel() = ScanModel(
    id,
    projectId,
    meshUrl,
    depthQuality,
    notes,
    createdAt
)
