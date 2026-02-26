package com.example.sublandlord.data.remote.dto

import com.google.gson.annotations.SerializedName

// Property DTO
data class PropertyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("type")
    val type: String,
    @SerializedName("area")
    val area: Float,
    @SerializedName("floor")
    val floor: String,
    @SerializedName("total_rooms")
    val totalRooms: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("amenities")
    val amenities: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("purchase_price")
    val purchasePrice: Float?,
    @SerializedName("decoration_cost")
    val decorationCost: Float?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

// Room DTO
data class RoomDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("property_id")
    val propertyId: String,
    @SerializedName("room_number")
    val roomNumber: String,
    @SerializedName("area")
    val area: Float,
    @SerializedName("floor")
    val floor: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("current_rent_price")
    val currentRentPrice: Float,
    @SerializedName("base_rent_price")
    val baseRentPrice: Float,
    @SerializedName("status")
    val status: String,
    @SerializedName("amenities")
    val amenities: List<String>,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("max_occupants")
    val maxOccupants: Int,
    @SerializedName("current_occupants")
    val currentOccupants: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

// Landlord DTO
data class LandlordDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("id_card")
    val idCard: String?,
    @SerializedName("wechat")
    val wechat: String?,
    @SerializedName("bank_info")
    val bankInfo: BankInfoDto?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

data class BankInfoDto(
    @SerializedName("bank_name")
    val bankName: String,
    @SerializedName("account_number")
    val accountNumber: String,
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("branch_name")
    val branchName: String?
)

// Tenant DTO
data class TenantDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("id_card")
    val idCard: String?,
    @SerializedName("wechat")
    val wechat: String?,
    @SerializedName("emergency_contact")
    val emergencyContact: ContactDto?,
    @SerializedName("room_id")
    val roomId: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("credit_score")
    val creditScore: Int?,
    @SerializedName("documents")
    val documents: List<DocumentDto>,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

data class ContactDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("relationship")
    val relationship: String
)

data class DocumentDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("expiry_date")
    val expiryDate: Long?
)

// Landlord Contract DTO
data class LandlordContractDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("property_id")
    val propertyId: String,
    @SerializedName("landlord_id")
    val landlordId: String,
    @SerializedName("start_date")
    val startDate: Long,
    @SerializedName("end_date")
    val endDate: Long,
    @SerializedName("monthly_rent")
    val monthlyRent: Float,
    @SerializedName("deposit")
    val deposit: Float,
    @SerializedName("payment_frequency")
    val paymentFrequency: String,
    @SerializedName("payment_day")
    val paymentDay: Int,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("late_fee")
    val lateFee: LateFeePolicyDto?,
    @SerializedName("status")
    val status: String,
    @SerializedName("contract_url")
    val contractUrl: String?,
    @SerializedName("signed_date")
    val signedDate: Long?,
    @SerializedName("renewal_history")
    val renewalHistory: List<RenewalRecordDto>,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

data class LateFeePolicyDto(
    @SerializedName("grace_period_days")
    val gracePeriodDays: Int,
    @SerializedName("daily_late_fee")
    val dailyLateFee: Float,
    @SerializedName("max_late_fee")
    val maxLateFee: Float
)

data class RenewalRecordDto(
    @SerializedName("old_end_date")
    val oldEndDate: Long,
    @SerializedName("new_end_date")
    val newEndDate: Long,
    @SerializedName("old_rent")
    val oldRent: Float,
    @SerializedName("new_rent")
    val newRent: Float,
    @SerializedName("renewal_date")
    val renewalDate: Long
)

// Lease DTO
data class LeaseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("room_id")
    val roomId: String,
    @SerializedName("tenant_id")
    val tenantId: String,
    @SerializedName("start_date")
    val startDate: Long,
    @SerializedName("end_date")
    val endDate: Long,
    @SerializedName("monthly_rent")
    val monthlyRent: Float,
    @SerializedName("deposit")
    val deposit: Float,
    @SerializedName("payment_frequency")
    val paymentFrequency: String,
    @SerializedName("payment_day")
    val paymentDay: Int,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("utilities")
    val utilities: UtilitiesPolicyDto,
    @SerializedName("late_fee")
    val lateFee: LateFeePolicyDto?,
    @SerializedName("status")
    val status: String,
    @SerializedName("contract_url")
    val contractUrl: String?,
    @SerializedName("signed_date")
    val signedDate: Long?,
    @SerializedName("renewal_history")
    val renewalHistory: List<RenewalRecordDto>,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

data class UtilitiesPolicyDto(
    @SerializedName("water_rate")
    val waterRate: Float?,
    @SerializedName("electricity_rate")
    val electricityRate: Float?,
    @SerializedName("include_utilities")
    val includeUtilities: Boolean,
    @SerializedName("include_gas")
    val includeGas: Boolean
)

// Transaction DTO
data class TransactionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("date")
    val date: Long,
    @SerializedName("property_id")
    val propertyId: String?,
    @SerializedName("room_id")
    val roomId: String?,
    @SerializedName("contract_id")
    val contractId: String?,
    @SerializedName("related_party_id")
    val relatedPartyId: String?,
    @SerializedName("related_party_name")
    val relatedPartyName: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("proof_images")
    val proofImages: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

// Reminder DTO
data class ReminderDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("target_date")
    val targetDate: Long,
    @SerializedName("advance_days")
    val advanceDays: Int?,
    @SerializedName("related_entity_id")
    val relatedEntityId: String?,
    @SerializedName("related_entity_type")
    val relatedEntityType: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("sent_dates")
    val sentDates: List<Long>,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)

// Common response wrapper
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?
)
