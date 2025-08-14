package com.example.arinteriordesigner.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.arinteriordesigner.data.entity.AssetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {
    @Upsert
    suspend fun upsertAll(items: List<AssetEntity>)

    @Query("SELECT * FROM asset ORDER BY name ASC")
    fun observeAll(): Flow<List<AssetEntity>>

    @Query("SELECT * FROM asset WHERE name LIKE '%' || :q || '%'")
    fun searchByName(q: String): Flow<List<AssetEntity>>
}