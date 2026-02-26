package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.PropertyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {
    @Query("SELECT * FROM properties ORDER BY updatedAt DESC")
    fun getAllProperties(): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE id = :propertyId")
    fun getPropertyById(propertyId: String): Flow<PropertyEntity?>

    @Query("SELECT * FROM properties WHERE status = :status")
    fun getPropertiesByStatus(status: com.example.sublandlord.data.local.entity.PropertyStatus): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE city = :city ORDER BY updatedAt DESC")
    fun getPropertiesByCity(city: String): Flow<List<PropertyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(property: PropertyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(properties: List<PropertyEntity>)

    @Update
    suspend fun updateProperty(property: PropertyEntity)

    @Delete
    suspend fun deleteProperty(property: PropertyEntity)

    @Query("DELETE FROM properties WHERE id = :propertyId")
    suspend fun deletePropertyById(propertyId: String)

    @Query("DELETE FROM properties")
    suspend fun deleteAllProperties()
}
