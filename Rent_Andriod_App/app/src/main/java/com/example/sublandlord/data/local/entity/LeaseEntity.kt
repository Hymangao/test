package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters

data class UtilitiesPolicy(
    val waterRate: Float?,
    val electricityRate: Float?,
    val includeUtilities: Boolean,
    val includeGas: Boolean
)

@Entity(
    tableName = "leases",
    foreignKeys = [
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TenantEntity::class,
            parentColumns = ["id"],
            childColumns = ["tenantId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["roomId"]),
        Index(value = ["tenantId"])
    ]
)
@TypeConverters(Converters::class)
data class LeaseEntity(
    @PrimaryKey
    val id: String,
    val roomId: String,
    val tenantId: String,
    val startDate: Long,
    val endDate: Long,
    val monthlyRent: Float,
    val deposit: Float,
    val paymentFrequency: PaymentFrequency,
    val paymentDay: Int,
    val paymentMethod: PaymentMethod,
    val utilities: UtilitiesPolicy,
    val lateFee: LateFeePolicy?,
    val status: ContractStatus,
    val contractUrl: String?,
    val signedDate: Long?,
    val renewalHistory: List<RenewalRecord>,
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
