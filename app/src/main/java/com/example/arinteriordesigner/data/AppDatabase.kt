package com.example.arinteriordesigner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.arinteriordesigner.data.dao.ProjectDao
import com.example.arinteriordesigner.data.entity.ProjectEntity

@Database(
    entities = [ProjectEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}