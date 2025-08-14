package com.example.arinteriordesigner.domain.model

import androidx.room.PrimaryKey
import com.example.arinteriordesigner.data.entity.ScanEntity
import kotlin.String

data class AssetModel(
    val id: String,
    val name: String,
    val glbUrl: String,
    val widthM: Float,
    val depthM: Float,
    val heightM: Float,
    val tags: List<String>?,
    val lods: List<LodMeta>?
)

fun AssetModel.toModel() = AssetModel(
    id,
    name,
    glbUrl,
    widthM,
    depthM,
    heightM,
    tags,
    lods,
)