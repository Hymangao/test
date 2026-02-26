package com.example.sublandlord.data.repository

import com.example.sublandlord.data.local.dao.PropertyDao
import com.example.sublandlord.data.local.entity.PropertyEntity
import com.example.sublandlord.data.remote.dto.PropertyDto
import com.example.sublandlord.data.remote.api.SubLandlordApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PropertyRepository @Inject constructor(
    private val propertyDao: PropertyDao,
    private val api: SubLandlordApi
) {

    // Local data sources
    fun getAllProperties(): Flow<List<PropertyEntity>> {
        return propertyDao.getAllProperties()
    }

    fun getPropertyById(propertyId: String): Flow<PropertyEntity?> {
        return propertyDao.getPropertyById(propertyId)
    }

    fun getPropertiesByStatus(status: com.example.sublandlord.data.local.entity.PropertyStatus): Flow<List<PropertyEntity>> {
        return propertyDao.getPropertiesByStatus(status)
    }

    // Remote data sources
    suspend fun syncProperties(): Result<List<PropertyDto>> {
        return try {
            val response = api.getProperties()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createProperty(property: PropertyEntity): Result<PropertyEntity> {
        return try {
            // First try to create remotely
            val dto = property.toDto()
            val response = api.createProperty(dto)

            if (response.isSuccessful && response.body() != null) {
                // Save locally
                propertyDao.insertProperty(response.body()!!.toEntity())
                Result.success(response.body()!!.toEntity())
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            // Save locally only if network fails
            propertyDao.insertProperty(property)
            Result.success(property)
        }
    }

    suspend fun updateProperty(property: PropertyEntity): Result<PropertyEntity> {
        return try {
            val dto = property.toDto()
            val response = api.updateProperty(property.id, dto)

            if (response.isSuccessful && response.body() != null) {
                propertyDao.updateProperty(response.body()!!.toEntity())
                Result.success(response.body()!!.toEntity())
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            propertyDao.updateProperty(property)
            Result.success(property)
        }
    }

    suspend fun deleteProperty(propertyId: String): Result<Unit> {
        return try {
            val response = api.deleteProperty(propertyId)
            if (response.isSuccessful) {
                propertyDao.deletePropertyById(propertyId)
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            propertyDao.deletePropertyById(propertyId)
            Result.success(Unit)
        }
    }
}

// Extension functions for Entity <-> DTO conversion
private fun PropertyEntity.toDto(): PropertyDto {
    return PropertyDto(
        id = id,
        name = name,
        address = address,
        city = city,
        district = district,
        latitude = latitude,
        longitude = longitude,
        type = type.name,
        area = area,
        floor = floor,
        totalRooms = totalRooms,
        status = status.name,
        images = emptyList(), // Parse from JSON
        amenities = emptyList(), // Parse from JSON
        description = description,
        purchasePrice = purchasePrice,
        decorationCost = decorationCost,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

private fun PropertyDto.toEntity(): PropertyEntity {
    return PropertyEntity(
        id = id,
        name = name,
        address = address,
        city = city,
        district = district,
        latitude = latitude,
        longitude = longitude,
        type = com.example.sublandlord.data.local.entity.PropertyType.valueOf(type),
        area = area,
        floor = floor,
        totalRooms = totalRooms,
        status = com.example.sublandlord.data.local.entity.PropertyStatus.valueOf(status),
        images = "", // Serialize to JSON
        amenities = "", // Serialize to JSON
        description = description,
        purchasePrice = purchasePrice,
        decorationCost = decorationCost,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
