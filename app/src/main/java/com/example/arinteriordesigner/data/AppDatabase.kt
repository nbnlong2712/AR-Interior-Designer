package com.example.arinteriordesigner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.arinteriordesigner.core.utils.Converter
import com.example.arinteriordesigner.data.dao.AssetDao
import com.example.arinteriordesigner.data.dao.PlacementDao
import com.example.arinteriordesigner.data.dao.ProjectDao
import com.example.arinteriordesigner.data.dao.ScanDao
import com.example.arinteriordesigner.data.entity.AssetEntity
import com.example.arinteriordesigner.data.entity.PlacementEntity
import com.example.arinteriordesigner.data.entity.ProjectEntity
import com.example.arinteriordesigner.data.entity.ScanEntity

@Database(
    entities = [ProjectEntity::class,
        ScanEntity::class,
        AssetEntity::class,
        PlacementEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun scanDao(): ScanDao
    abstract fun assetDao(): AssetDao
    abstract fun placementDao(): PlacementDao
}