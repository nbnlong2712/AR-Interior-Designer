package com.example.arinteriordesigner.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.arinteriordesigner.domain.model.LodMeta

@Entity(
    tableName = "asset",
    indices = [Index("name")]
)
data class AssetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val glbUrl: String,
    val widthM: Float,
    val depthM: Float,
    val heightM: Float,
    val tags: List<String>?,
    val lods: List<LodMeta>?
)