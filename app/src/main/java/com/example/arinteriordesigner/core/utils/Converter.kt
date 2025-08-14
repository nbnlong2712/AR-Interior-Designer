package com.example.arinteriordesigner.core.utils

import androidx.room.TypeConverter
import com.example.arinteriordesigner.domain.model.AnchorPose
import com.example.arinteriordesigner.domain.model.LodMeta
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converter {
    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    @TypeConverter
    @JvmStatic
    fun listStringToJson(v: List<String>?): String? = v?.let { json.encodeToString(it) }

    @TypeConverter
    @JvmStatic
    fun jsonToListString(s: String?): List<String>? = s?.let { json.decodeFromString(it) }

    @TypeConverter
    @JvmStatic
    fun anchorsToJson(v: List<AnchorPose>?): String? = v?.let { json.encodeToString(it) }

    @TypeConverter
    @JvmStatic
    fun jsonToAnchors(s: String?): List<AnchorPose>? = s?.let { json.decodeFromString(it) }

    @TypeConverter
    @JvmStatic
    fun lodsToJson(v: List<LodMeta>?): String? = v?.let { json.encodeToString(it) }

    @TypeConverter
    @JvmStatic
    fun jsonToLods(s: String?): List<LodMeta>? = s?.let { json.decodeFromString(it) }
}