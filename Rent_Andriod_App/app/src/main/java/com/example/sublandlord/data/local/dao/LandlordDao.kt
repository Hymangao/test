package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.LandlordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LandlordDao {
    @Query("SELECT * FROM landlords ORDER BY updatedAt DESC")
    fun getAllLandlords(): Flow<List<LandlordEntity>>

    @Query("SELECT * FROM landlords WHERE id = :landlordId")
    fun getLandlordById(landlordId: String): Flow<LandlordEntity?>

    @Query("SELECT * FROM landlords WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'")
    fun searchLandlords(query: String): Flow<List<LandlordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLandlord(landlord: LandlordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLandlords(landlords: List<LandlordEntity>)

    @Update
    suspend fun updateLandlord(landlord: LandlordEntity)

    @Delete
    suspend fun deleteLandlord(landlord: LandlordEntity)

    @Query("DELETE FROM landlords WHERE id = :landlordId")
    suspend fun deleteLandlordById(landlordId: String)
}
