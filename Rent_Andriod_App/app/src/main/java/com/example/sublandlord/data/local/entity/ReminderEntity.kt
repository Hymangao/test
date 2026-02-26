package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters

enum class ReminderType {
    RENT_COLLECTION,
    RENT_PAYMENT,
    CONTRACT_EXPIRY,
    UTILITY_PAYMENT,
    MAINTENANCE,
    CUSTOM
}

enum class RelatedEntityType {
    LEASE,
    LANDLORD_CONTRACT,
    PROPERTY,
    ROOM
}

enum class ReminderStatus {
    PENDING,
    SENT,
    COMPLETED,
    CANCELLED
}

enum class ReminderPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
}

@Entity(tableName = "reminders")
@TypeConverters(Converters::class)
data class ReminderEntity(
    @PrimaryKey
    val id: String,
    val type: ReminderType,
    val title: String,
    val message: String,
    val targetDate: Long,
    val advanceDays: Int?,
    val relatedEntityId: String?,
    val relatedEntityType: RelatedEntityType?,
    val status: ReminderStatus,
    val priority: ReminderPriority,
    val sentDates: List<Long>,
    val createdAt: Long,
    val updatedAt: Long
)
