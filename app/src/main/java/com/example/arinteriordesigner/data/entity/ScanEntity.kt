package com.example.arinteriordesigner.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scan",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("projectId"), Index(value = ["createdAt"])]
)
data class ScanEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val meshUrl: String?,
    val depthQuality: Int?,
    val notes: String?,
    val createdAt: Long
)