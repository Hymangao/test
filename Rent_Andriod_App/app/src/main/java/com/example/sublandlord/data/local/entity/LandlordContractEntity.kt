package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters

enum class PaymentFrequency {
    MONTHLY,
    QUARTERLY,
    YEARLY
}

enum class PaymentMethod {
    CASH,
    BANK_TRANSFER,
    WECHAT,
    ALIPAY
}

enum class ContractStatus {
    DRAFT,
    ACTIVE,
    EXPIRED,
    TERMINATED
}

data class LateFeePolicy(
    val gracePeriodDays: Int,
    val dailyLateFee: Float,
    val maxLateFee: Float
)

data class RenewalRecord(
    val oldEndDate: Long,
    val newEndDate: Long,
    val oldRent: Float,
    val newRent: Float,
    val renewalDate: Long
)

@Entity(
    tableName = "landlord_contracts",
    foreignKeys = [
        ForeignKey(
            entity = PropertyEntity::class,
            parentColumns = ["id"],
            childColumns = ["propertyId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LandlordEntity::class,
            parentColumns = ["id"],
            childColumns = ["landlordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["propertyId"]),
        Index(value = ["landlordId"])
    ]
)
@TypeConverters(Converters::class)
data class LandlordContractEntity(
    @PrimaryKey
    val id: String,
    val propertyId: String,
    val landlordId: String,
    val startDate: Long,
    val endDate: Long,
    val monthlyRent: Float,
    val deposit: Float,
    val paymentFrequency: PaymentFrequency,
    val paymentDay: Int,
    val paymentMethod: PaymentMethod,
    val lateFee: LateFeePolicy?,
    val status: ContractStatus,
    val contractUrl: String?,
    val signedDate: Long?,
    val renewalHistory: List<RenewalRecord>,
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
