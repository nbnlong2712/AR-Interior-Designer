package com.example.arinteriordesigner.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "projects",
    indices = [
        Index(value = ["lastOpenedAt"]),
        Index(value = ["name"])
    ]
)
data class ProjectEntity(
    @PrimaryKey
    val uid: String,
    val name: String,
    val description: String? = null,
    val createdAt: Long,
    val updatedAt: Long,
    val thumbnailUri: String? = null,
    val scanId: String? = null,
    val lastOpenedAt: Long
)