package com.example.arinteriordesigner.domain.model

import com.example.arinteriordesigner.data.entity.PlacementEntity

data class PlacementModel(
    val id: String,
    val projectId: String,
    val assetId: String,
    val position: Vec3,
    val rotation: Quat,
    val scale: Vec3 = Vec3(1f, 1f, 1f),
    val surface: String = "HORIZONTAL_UP",
    val createdAt: Long,
    val updatedAt: Long
)

fun PlacementEntity.toModel() = PlacementModel(
    id,
    projectId,
    assetId,
    position,
    rotation,
    scale,
    surface,
    createdAt,
    updatedAt
)