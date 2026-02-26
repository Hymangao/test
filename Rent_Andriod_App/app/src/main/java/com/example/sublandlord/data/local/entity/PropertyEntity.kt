package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class PropertyType {
    ENTIRE_RENT,
    SHARED_RENT
}

enum class PropertyStatus {
    VACANT,
    RENTED,
    DECORATING,
    MAINTENANCE
}

@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val district: String,
    val latitude: Double?,
    val longitude: Double?,
    val type: PropertyType,
    val area: Float,
    val floor: String,
    val totalRooms: Int,
    val status: PropertyStatus,
    val images: String, // JSON array of URLs
    val amenities: String, // JSON array of strings
    val description: String,
    val purchasePrice: Float?,
    val decorationCost: Float?,
    val createdAt: Long,
    val updatedAt: Long
)
