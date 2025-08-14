package com.example.arinteriordesigner.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.arinteriordesigner.data.entity.PlacementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacementDao {
    @Upsert
    suspend fun upsert(placement: PlacementEntity)

    @Query("DELETE FROM placement WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM placement WHERE projectId = :projectId")
    fun observeForProject(projectId: String): Flow<List<PlacementEntity>>
}