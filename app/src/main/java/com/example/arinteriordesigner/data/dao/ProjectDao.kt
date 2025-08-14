package com.example.arinteriordesigner.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.arinteriordesigner.data.entity.ProjectEntity
import com.example.arinteriordesigner.data.entity.ProjectWithPlacements
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE id = :id")
    fun getProjectById(id: String): Flow<ProjectEntity>

    @Upsert
    suspend fun upsert(projectEntity: ProjectEntity)

    @Query("DELETE FROM projects WHERE id = :id")
    suspend fun delete(id: String)

    @Transaction
    @Query("SELECT * FROM projects WHERE id = :id")
    fun observeWithPlacements(id: String): Flow<ProjectWithPlacements?>

    @Query("UPDATE projects SET updatedAt = :updatedAt WHERE id = :id")
    suspend fun touch(id: String, updatedAt: Long)
}