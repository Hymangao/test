package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

enum class RoomType {
    MASTER,
    SINGLE,
    SHARED,
    STUDIO
}

enum class RoomStatus {
    VACANT,
    OCCUPIED,
    MAINTENANCE
}

@Entity(
    tableName = "rooms",
    foreignKeys = [
        ForeignKey(
            entity = PropertyEntity::class,
            parentColumns = ["id"],
            childColumns = ["propertyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["propertyId"])]
)
data class RoomEntity(
    @PrimaryKey
    val id: String,
    val propertyId: String,
    val roomNumber: String,
    val area: Float,
    val floor: String,
    val type: RoomType,
    val currentRentPrice: Float,
    val baseRentPrice: Float,
    val status: RoomStatus,
    val amenities: String, // JSON array
    val images: String, // JSON array
    val maxOccupants: Int,
    val currentOccupants: Int,
    val createdAt: Long,
    val updatedAt: Long
)
