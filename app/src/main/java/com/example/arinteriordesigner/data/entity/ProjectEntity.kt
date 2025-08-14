package com.example.arinteriordesigner.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.arinteriordesigner.domain.model.AnchorPose

@Entity(
    tableName = "projects",
    indices = [Index(value = ["updatedAt"])]
)
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val createdAt: Long,
    val updatedAt: Long,
    val roomMeshRef: String?,
    val anchors: List<AnchorPose>?
)