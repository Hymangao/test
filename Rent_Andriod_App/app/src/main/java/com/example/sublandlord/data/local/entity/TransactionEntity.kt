package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class TransactionCategory {
    // Income categories
    RENT,
    DEPOSIT_RETURN,
    UTILITY_CHARGE,
    // Expense categories
    RENT_TO_LANDLORD,
    MAINTENANCE,
    UTILITY_PAYMENT,
    DECORATION,
    FURNITURE,
    MISCELLANEOUS
}

enum class TransactionStatus {
    PENDING,
    COMPLETED,
    CANCELLED
}

@Entity(tableName = "transactions")
@TypeConverters(Converters::class)
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val type: TransactionType,
    val category: TransactionCategory,
    val amount: Float,
    val date: Long,
    val propertyId: String?,
    val roomId: String?,
    val contractId: String?,
    val relatedPartyId: String?,
    val relatedPartyName: String?,
    val status: TransactionStatus,
    val paymentMethod: PaymentMethod,
    val proofImages: String, // JSON array of URLs
    val description: String,
    val tags: List<String>,
    val createdAt: Long,
    val updatedAt: Long
)
