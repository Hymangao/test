package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters

enum class TenantStatus {
    ACTIVE,
    PENDING,
    EXPIRED,
    BLACKLISTED
}

enum class DocumentType {
    ID_CARD,
    STUDENT_CARD,
    PASSPORT
}

data class Contact(
    val name: String,
    val phone: String,
    val relationship: String
)

data class Document(
    val type: DocumentType,
    val imageUrl: String,
    val expiryDate: Long?
)

@Entity(
    tableName = "tenants",
    foreignKeys = [
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(value = ["roomId"])]
)
@TypeConverters(Converters::class)
data class TenantEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    val idCard: String?,
    val wechat: String?,
    val emergencyContact: Contact?,
    val roomId: String?,
    val status: TenantStatus,
    val creditScore: Int?,
    val documents: List<Document>,
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
