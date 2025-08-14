package com.example.arinteriordesigner.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Vec3(val x: Float, val y: Float, val z: Float)

@Serializable
data class Quat(val x: Float, val y: Float, val z: Float, val w: Float)

@Serializable
data class AnchorPose(
    val t: Vec3,
    val r: Quat
)

@Serializable
data class LodMeta(
    val level: Int,
    val glbUrl: String,
    val targetTris: Int? = null
)