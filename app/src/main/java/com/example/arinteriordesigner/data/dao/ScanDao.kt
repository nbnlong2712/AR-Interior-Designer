package com.example.arinteriordesigner.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.arinteriordesigner.data.entity.ScanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao {
    @Upsert
    suspend fun upsert(scan: ScanEntity)

    @Query("SELECT * FROM scan WHERE projectId = :projectId ORDER BY createdAt DESC")
    fun observeByProject(projectId: String): Flow<List<ScanEntity>>
}