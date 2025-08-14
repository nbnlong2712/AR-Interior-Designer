package com.example.arinteriordesigner.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.arinteriordesigner.domain.model.Quat
import com.example.arinteriordesigner.domain.model.Vec3

@Entity(
    tableName = "placement",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AssetEntity::class,
            parentColumns = ["id"],
            childColumns = ["assetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("projectId"), Index("assetId")]
)
data class PlacementEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val assetId: String,

    @Embedded(prefix = "pos_") val position: Vec3,
    @Embedded(prefix = "rot_") val rotation: Quat,
    @Embedded(prefix = "scl_") val scale: Vec3 = Vec3(1f, 1f, 1f),

    val surface: String = "HORIZONTAL_UP",
    val createdAt: Long,
    val updatedAt: Long
)

data class ProjectWithPlacements(
    @Embedded val project: ProjectEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "projectId"
    )
    val placements: List<PlacementEntity>
)