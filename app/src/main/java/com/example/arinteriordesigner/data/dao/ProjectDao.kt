package com.example.arinteriordesigner.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.arinteriordesigner.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE uid = :id")
    fun getProjectById(id: String): Flow<ProjectEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(projectEntity: ProjectEntity)

    @Query("DELETE FROM projects WHERE uid = :id")
    suspend fun delete(id: String)

    @Update
    suspend fun update(projectEntity: ProjectEntity)
}